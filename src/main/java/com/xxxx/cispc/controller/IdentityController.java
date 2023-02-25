package com.xxxx.cispc.controller;

import com.xxxx.cispc.base.BaseController;
import com.xxxx.cispc.base.ResultInfo;
import com.xxxx.cispc.query.IdentityQuery;
import com.xxxx.cispc.service.IdentityService;
import com.xxxx.cispc.vo.Identity;
import com.xxxx.cispc.vo.Table;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("identity")
public class IdentityController extends BaseController {

    @Resource
    private IdentityService identityService;


    /**
     * 进入到身份管理页面
     * @return 返回视图名称
     */
    @RequestMapping("index")
    public String index(){
        return "identity/identity";
    }


    /**
     * 多条件查询角色信息
     * @param identityQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public Map<String,Object> queryIdentityByParams(IdentityQuery identityQuery){
        return identityService.queryIdentityByParams(identityQuery);
    }

    /**
     * 查询所有的身份信息
     * @return
     */
    @RequestMapping("allIdentity")
    @ResponseBody
    public List<Identity>  queryAllIdentity(){
        return identityService.queryAllIdentity();
    }

    /**
     * 进入到添加或更新table页面
     * @return 返回添加视图
     */
    @RequestMapping("toAddOrUpdate")
    public String toAddOrUpdate(Integer identityId,HttpServletRequest request){
        //判断传入的是否含有角色id
        if (identityId!=null){
            //如果有查询对应的角色记录,放到请求域中
            Identity identity=identityService.selectByPrimaryKey(identityId);
            request.setAttribute("identity",identity);
        }
        //返回视图名称
        return "identity/add_update";
    }

    /**
     * 进行添加操作
     * @param identity 要添加的数据
     * @return 结果
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addIdentity(Identity identity){
        identityService.addTable(identity);
        return success("添加操作执行成功!");
    }


    /**
     * 进行更新操作
     * @param identity 要更新的数据
     * @return 结果
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateTable(Identity identity){
        identityService.updateIdentity(identity);
        return success("更新操作执行成功!");
    }

    /**
     * 进行删除操作
     * @param identityIds 要删除的数据id
     * @return 返回结果
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteTable(Integer[] identityIds){
        identityService.deleteTable(identityIds);
        return success("删除操作执行成功!");
    }


}
