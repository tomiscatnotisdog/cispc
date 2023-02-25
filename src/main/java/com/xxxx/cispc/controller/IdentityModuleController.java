package com.xxxx.cispc.controller;

import com.xxxx.cispc.base.BaseController;
import com.xxxx.cispc.base.ResultInfo;
import com.xxxx.cispc.model.IdentityModuleModel;
import com.xxxx.cispc.query.IdentityModuleQuery;
import com.xxxx.cispc.query.IdentityQuery;
import com.xxxx.cispc.service.IdentityMoudleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("identityModule")
public class IdentityModuleController extends BaseController {

    @Resource
    private IdentityMoudleService identityMoudleService;

    @RequestMapping("index")
    public String index(){
        return "identityModule/identityModule";
    }

    /**
     * 多条件查询角色信息权限信息 IdentityModuleQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public Map<String,Object> queryIdentityByParams(IdentityModuleQuery identityModuleQuery){
        return identityMoudleService.queryIdentityByParams(identityModuleQuery);
    }

    /**
     * 打开授权页面
     * @return
     */
    @RequestMapping("toWarrant")
    public String toWarrant(){
        return "identityModule/warrant";
    }

    /**
     * 授权
     * @return 返回结果
     */
    @RequestMapping("warrant")
    @ResponseBody
    public ResultInfo warrant(String identityName,String[] code){
        //调用授权方法
        identityMoudleService.warrant(identityName,code);
        //结果返回
        return success("授权成功!");
    }

    @ResponseBody
    @RequestMapping("delete")
    public ResultInfo delete(Integer id){
        identityMoudleService.deleteIdentityModule(id);
        return success("权限移除成功!");
    }


    /**
     * 通过用户身份查询用户所具有的权限
     * @param identity
     * @return
     */
    @ResponseBody
    @RequestMapping("userHaveCode")
    public List<Integer> userHaveCode(String identity){//参数是是否名称
        return identityMoudleService.queryCodeByIdentityName(identity);
    }

}
