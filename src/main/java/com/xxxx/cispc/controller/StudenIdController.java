package com.xxxx.cispc.controller;

import com.xxxx.cispc.base.BaseController;
import com.xxxx.cispc.base.ResultInfo;
import com.xxxx.cispc.query.StuIdnameQuery;
import com.xxxx.cispc.service.StudenIdService;
import com.xxxx.cispc.vo.StuXueHao;
import com.xxxx.cispc.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("xuesheng")
public class StudenIdController extends BaseController {


    @Resource
    private StudenIdService studenIdService;

    /**查询多条件查询*/
    @RequestMapping("lisst")
    @ResponseBody
    public Map<String,Object> queryUsersByParams(StuIdnameQuery stuIdnameQuery){
        //根据条件查询
        Map<String, Object> map = studenIdService.queryStudentID(stuIdnameQuery);
        return map;
    }

    //  用户 页面
    @RequestMapping("xueuser")
    public String index(){
        return "student/studenteaveslip";
    }


//     /**添加*/
       @RequestMapping("save")
       @ResponseBody
       public ResultInfo saveStudent(StuXueHao xueHao){
           studenIdService.saveSaleChance(xueHao);
           return success("添加成功");
       }



//       添加或者更新操作

    @RequestMapping("studentxuesheng")
    public String addOrUpdateSaleChancePage(Integer id, HttpServletRequest request ){

        if (id!=null){
            //查询班级信息 通过ID查询用户对象
            StuXueHao stuxuehao= studenIdService.selectByPrimaryKey(id);
            System.out.println(stuxuehao);
            //存储 将数据设置到请求与中
            request.setAttribute("stuxuehao",stuxuehao);

        }
        return "student/studentqueries";
    }


    /**删除*/
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteStudent(Integer [] id){

        studenIdService.removeIed(id);

        return success("班级删除成功");

    }
//    更新
    /** 更新用户*/
    @RequestMapping("studentdelete")
    @ResponseBody
    public ResultInfo update(StuXueHao stuXueHao){

        studenIdService.changeUser(stuXueHao);

        return success("班级更新成功");
    }



}


