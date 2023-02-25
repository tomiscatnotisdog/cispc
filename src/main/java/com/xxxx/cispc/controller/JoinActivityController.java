package com.xxxx.cispc.controller;

import com.xxxx.cispc.base.BaseController;
import com.xxxx.cispc.base.ResultInfo;
import com.xxxx.cispc.dao.JoinActivityMapper;
import com.xxxx.cispc.dao.MakeFrendMapper;
import com.xxxx.cispc.model.MyjoinModel;
import com.xxxx.cispc.query.CommentQuery;
import com.xxxx.cispc.query.JoinActivityQuery;
import com.xxxx.cispc.service.JoinActivityService;
import com.xxxx.cispc.utils.AssertUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@RequestMapping("join")
public class JoinActivityController extends BaseController {

    @Resource
    private JoinActivityService joinActivityService;

    @Resource
    private MakeFrendMapper makeFrendMapper;

    @Resource
    private JoinActivityMapper joinActivityMapper;

    @RequestMapping("index")
    public String index(){
        return "joinActivity/joinActivity";
    }

    @GetMapping("frend")
    @ResponseBody
    public ResultInfo joinFrend(Integer id, HttpServletRequest request
                                            , String activityName
                                            , String createManPhone, String createMan, String joinMan){
     /*   Cookie[] cookies = request.getCookies();
        String joinMan = String.valueOf(cookies[1]);*/
     /*   AssertUtil.isTrue(id == null || makeFrendMapper.selectByPrimaryKey(id) == null,"待加入记录不存在" );
        AssertUtil.isTrue(StringUtils.isBlank(activityName),"活动名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(createManPhone),"手机号码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(createMan) ,"创建人不能为空");
*/
        joinActivityService.JoinFrend(id,activityName,createMan,createManPhone,joinMan);
        return success("加入活动成功");

    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryAlljoinFrend(JoinActivityQuery joinActivityQuery){
        System.out.println(joinActivityQuery.getJoinMan());
        String joinMan = URLDecoder.decode(joinActivityQuery.getJoinMan());
        joinActivityQuery.setJoinMan(joinMan);
        /*return makeFrendService.queryAllMakeFrend(makeFrendQuery);*/
//        System.out.println(makeFrendService.queryByParamsForTable(makeFrendQuery));
        return joinActivityService.queryByParamsForTable(joinActivityQuery);
    }
    @RequestMapping("addcomment")
    public String addcomment(Integer id,HttpServletRequest request,String joinMan){
        if (id != null){
            MyjoinModel joinActivity = joinActivityMapper.selectByMakeFrendId(id,joinMan);
            request.setAttribute("joinActivity",joinActivity);
            System.out.println(joinActivity.getMakeFrendId());
        }
        return "joinActivity/comment";
    }

    @RequestMapping("updatecomment")
    @ResponseBody
    public ResultInfo updatecomment(Integer id, HttpServletRequest request, String comment, String joinMan){

        joinActivityService.updateComment(comment,id,joinMan);

        return success("评论成功");
    }


    @RequestMapping("myfrend")
    public String myfrend(){
        return "myFrend/myFrend";
    }

    @RequestMapping("createlist")
    @ResponseBody
    public Map<String,Object> queryAllMyCreate(JoinActivityQuery joinActivityQuery){
        System.out.println(joinActivityService.queryByParamsForTableMyCreate(joinActivityQuery).toString());
        return joinActivityService.queryByParamsForTableMyCreate(joinActivityQuery);
    }


    @RequestMapping("checkComment")
    @ResponseBody
    public Map<String,Object> checkComment(CommentQuery commentQuery){
        System.out.println(joinActivityService.queryByParamsForTableMyJoin(commentQuery).toString());
        return joinActivityService.queryByParamsForTableMyJoin(commentQuery);
    }

    @RequestMapping("frendunjoin")
    @ResponseBody
    public ResultInfo frendunjoin(Integer id, String joinMan){

        AssertUtil.isTrue(id == null,"待需取消记录存在");
        joinActivityService.frendunjoin(id,joinMan);

        return success("取消成功");
    }
}
