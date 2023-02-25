package com.xxxx.cispc.controller;


import com.xxxx.cispc.base.BaseController;
import com.xxxx.cispc.base.ResultInfo;
import com.xxxx.cispc.dao.MakeFrendMapper;
import com.xxxx.cispc.query.MakeFrendQuery;
import com.xxxx.cispc.service.MakeFrendService;
import com.xxxx.cispc.vo.MakeFrend;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("frend")
public class MakeFrendController extends BaseController {

    @Resource
    private MakeFrendService makeFrendService;

    @Resource
    private MakeFrendMapper makeFrendMapper;

    @RequestMapping("index")
    public String index(){
        return "makeFrend/makefrend";
    }


//    查询全活动列表
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryAllMakeFrend(MakeFrendQuery makeFrendQuery){

        return makeFrendService.queryByParamsForTable(makeFrendQuery);
    }

    @RequestMapping("addFrendPage")
    public String addFrendPage(){
        return "makeFrend/add_update";
    }

    @RequestMapping("updatefrend")
    public String updatefend(Integer id,HttpServletRequest request){
        if (id != null){
            MakeFrend makeFrends = makeFrendMapper.selectByPrimaryKey(id);
            request.setAttribute("makeFrends",makeFrends);
            System.out.println(makeFrends.toString());
        }
        return "createupdate/update";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addOFrend(MakeFrend makeFrend){
        makeFrendService.addFrend(makeFrend);
        return success("添加成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteFrend(Integer id, HttpServletRequest request){

        makeFrendService.deleteFrendById(id);
        return success("删除成功");
    }



//    对发起的活动进行更新操作
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateFrend(MakeFrend makeFrend, Integer id){
        makeFrendService.updateFrend(makeFrend,id);
        return success("更新成功");
    }

    @RequestMapping("checkinfo")
    public String checkinfo(Integer id,HttpServletRequest request){
        if (id != null){
            MakeFrend checkinfo = makeFrendMapper.selectByPrimaryKey(id);
            request.setAttribute("checkinfo",checkinfo);
            System.out.println(checkinfo.toString());
        }
        return "createupdate/data";
    }


}
