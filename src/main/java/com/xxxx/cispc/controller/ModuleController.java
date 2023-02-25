package com.xxxx.cispc.controller;

import com.xxxx.cispc.base.BaseController;
import com.xxxx.cispc.base.ResultInfo;
import com.xxxx.cispc.query.ModuleQuery;
import com.xxxx.cispc.service.ModuleService;
import com.xxxx.cispc.vo.Module;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController{

    //依赖注入
    @Resource
    private ModuleService moduleService;

    /**
     * 进入到资源管理页面
     * @return 返回主页视图
     */
    @RequestMapping("index")
    public String index(){
        return "module/module";
    }

    /**
     * 进入到添加或更新资源页面
     * @return 返回添加视图
     */
    @RequestMapping("toAddOrUpdate")
    public String toAddOrUpdate(Integer moduleId, HttpServletRequest request){
        //判断传入的是否含有角色id
        if (moduleId!=null){
            //如果有查询对应的角色记录,放到请求域中
            Module module=moduleService.selectByPrimaryKey(moduleId);
            request.setAttribute("module",module);
        }
        //返回视图名称
        return "module/add_update";
    }

    /**
     * 进行添加操作
     * @param Module 要添加的数据
     * @return 结果
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addModule(Module Module){
        moduleService.addTable(Module);
        return success("添加操作执行成功!");
    }

    /**
     * 进行更新操作
     * @param module 要添加的数据
     * @return 结果
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateModule(Module module){
        moduleService.updateModule(module);
        return success("更新操作执行成功!");
    }


    /**
     * 打开授权页面
     * @return 返回角色授权视图
     */
    @RequestMapping("toGrantPage")
    public String toGrantPage(Integer identityId, HttpServletRequest request){
        //将要授权的角色id返回给前台
        request.setAttribute("identityId",identityId);
        return "/identity/grant";
    }

    /**
     * 进行删除操作
     * @param moduleIds 要删除的数据id
     * @return 返回结果
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteModule(Integer[] moduleIds){
        moduleService.deleteMoudle(moduleIds);
        return success("删除操作执行成功!");
    }




    /**
     * 多条件查询资源数据
     * @param moduleQuery 查询对象
     * @return 返回的马屁集合(code,mas,data,count)
     */
    @ResponseBody
    @RequestMapping("list")
    public Map<String, Object> queryTableList(ModuleQuery moduleQuery){
        return moduleService.queryTableList(moduleQuery);
    }

    /**
     * 通过所属资源查询资源记录(当前后台写死为0,'main')
     * @return main下的资源
     */
    @RequestMapping("queryParentId")
    @ResponseBody
    public List<Module> queryModulebyParentId(){
        return moduleService.queryModulebyParentId();
    }

    /**
     * 查询所有的一级资源(父id为0,且不是main)
     * @return 返回符合要求的资源信息
     */
    @ResponseBody
    @RequestMapping("moduleIsOne")
    public List<Module> queryModuleIsOne(){
        return moduleService.queryModuleIsOne();
    }
}
