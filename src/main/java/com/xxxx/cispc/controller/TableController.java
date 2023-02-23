package com.xxxx.cispc.controller;

import com.xxxx.cispc.base.BaseController;
import com.xxxx.cispc.base.ResultInfo;
import com.xxxx.cispc.query.TableQuery;
import com.xxxx.cispc.service.IdentityService;
import com.xxxx.cispc.service.TableService;
import com.xxxx.cispc.vo.Table;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * table表格展示(控制层)
 */
@Controller
@RequestMapping("table")
public class TableController extends BaseController {

    @Resource
    private TableService tableService;
    @Resource
    private IdentityService identityService;

    /**
     * 多条件查询table数据
     * @param tableQuery 查询对象
     * @return 返回的马屁集合(code,mas,data,count)
     */
    @ResponseBody
    @RequestMapping("list")
    public Map<String, Object> queryTableList(TableQuery tableQuery){
        return tableService.queryTableList(tableQuery);
    }

    /**
     * 进入到添加table页面
     * @return 返回添加视图
     */
    @RequestMapping("toAdd")
    public String toAdd(){
        //返回视图名称
        return "page/table_add";
    }

    /**
     * 进行添加操作
     * @param table 要添加的数据
     * @return 结果
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addTable(Table table){

        System.out.println(table);

        tableService.addTable(table);
        return success("添加操作执行成功!");
    }

    /**
     * 进入到编辑table页面
     * @return 返回修改视图
     */
    @RequestMapping("toUpdate")
    public String toUpdate(HttpServletRequest request,Integer tableId){
        //通过tableId查询数据,存放到请求域中
        tableService.queryTableById(request,tableId);
        return "page/table_update";
    }

    /**
     * 进行更新操作
     * @param table 要更新的数据
     * @return 结果
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateTable(Table table){
        tableService.updateTable(table);
        return success("更新操作执行成功!");
    }

    /**
     * 进行删除操作
     * @param tableIds 要删除的数据id
     * @return 返回结果
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteTable(Integer[] tableIds){
        tableService.deleteTable(tableIds);
        return success("删除操作执行成功!");
    }

}
