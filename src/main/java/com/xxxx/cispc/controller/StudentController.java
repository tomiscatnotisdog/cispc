package com.xxxx.cispc.controller;


import com.xxxx.cispc.base.BaseController;
import com.xxxx.cispc.base.ResultInfo;
import com.xxxx.cispc.query.StudentQuery;
import com.xxxx.cispc.service.StudentService;
import com.xxxx.cispc.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@RequestMapping("student")
@Controller
public class StudentController extends BaseController {

    //导入Service层的方法
    @Resource
    private StudentService studentService;



    /**查询多条件查询*/
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryUsersByParams(StudentQuery studentQuery){
        //根据条件查询
        Map<String, Object> map = studentService.queryUsersByParams(studentQuery);
        return map;
    }
//
    /**添加*/
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveStudent(Student student){
        studentService.saveStudent(student);
        return success("添加成功");
    }

    //  用户 页面
    @RequestMapping("index")
    public String index(){
        return "student/student";
    }



    /** 更新用户*/
        @RequestMapping("studentdelete")
    @ResponseBody
    public ResultInfo update(Student student){
        studentService.changeUser(student);
        return success("班级更新成功");
    }


    //    添加
    @RequestMapping("addOrUpdateDialog")
    public String addOrUpdateDialog(Integer id, HttpServletRequest request  ){

        if (id!=null){
            //查询班级信息 通过ID查询用户对象
            Student student= studentService.selectByPrimaryKey(id);
            //存储 将数据设置到请求与中
            request.setAttribute("student",student);

        }
        return "student/add_update";

    }




    /**删除*/
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteStudent(Integer [] id){

        studentService.removeIed(id);

        return success("班级删除成功");

    }





}
