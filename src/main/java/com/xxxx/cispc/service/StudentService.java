package com.xxxx.cispc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xxxx.cispc.base.BaseService;
import com.xxxx.cispc.dao.StudentMapper;
import com.xxxx.cispc.query.StudentQuery;
import com.xxxx.cispc.utils.AssertUtil;
import com.xxxx.cispc.utils.PhoneUtil;
import com.xxxx.cispc.vo.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService extends BaseService<Student,Integer> {

    //导入Mapeer层
    @Resource
    private StudentMapper studentMapper;

    //    根据查询 依赖Layui格式要求
    public Map<String,Object> queryUsersByParams(StudentQuery studentQuery ){
        //初始化分页数据
        PageHelper.startPage(studentQuery.getPage(),studentQuery.getLimit());
        //条件查询
//
        List<Student> ulist = studentMapper.selectByParams(studentQuery);
        //开始分页
        PageInfo<Student>plist=new PageInfo<>(ulist);
        //实列化Map
        Map<String,Object>map=new HashMap<>();
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        return map;
    }







    //    添加操作
    public void saveStudent(Student student){
        /**
         * 判断思路 saveStudent
         *    班级名称不能为空
         *     班级人数不能为空
         *     班主任名字不能为空
         *     班主任邮箱格式不能为空
         *     班主任电话不能为空
         *     所属年级 不能为空
         *     班级介绍 不能为空
         *
         * */
        checkStudent(student.getUserName(),student.getClazzName(),student.getEmail(),student.getAddresss(),student.getIntroducatio(),student.getPhone(),student.getGenderr(),student.getSon());

        student.setIsValid(1);

        AssertUtil.isTrue(insertSelective(student)<1,"用户记录添加失败!");

    }

    private void checkStudent(String userName, String clazzName, String email, String addresss, String introducatio, String phone, String genderr, String son) {
        AssertUtil.isTrue(son==null,"请输入班级号");
        AssertUtil.isTrue(genderr==null,"请输入你的性别");
        AssertUtil.isTrue(userName==null,"请输入老师名称");
        AssertUtil.isTrue(clazzName==null,"请输入班级名称");
        AssertUtil.isTrue(email==null,"请输入邮箱");
        AssertUtil.isTrue(addresss==null,"请输入班级人数");
        AssertUtil.isTrue(introducatio==null,"请输入班级介绍");
        AssertUtil.isTrue(!(PhoneUtil.isMobile(phone)),"手机号格式非法!");

   }


//
//
//
//
    //   更新操作
    public  void changeUser(Student student){
        //判断用户ID是否存在
        /**
         * 1.参数校验
         *     id 记录是否为空 数据存在
         *     班级名称不能为空
         *          *     班级人数不能为空
         *          *     班主任名字不能为空
         *          *     班主任邮箱格式不能为空
         *          *     班主任电话不能为空
         *          *     所属年级 不能为空
         *          *     班级介绍 不能为空
         * 3.执行更新
         *
         */
        Student temp = studentMapper.selectByPrimaryKey(student.getId());
        AssertUtil.isTrue(temp==null,"更新记录不存在");
        checkStudent(student.getUserName(),student.getClazzName(),student.getEmail(),student.getAddresss(),student.getIntroducatio(),student.getPhone(),student.getGenderr(),student.getSon());

        AssertUtil.isTrue(studentMapper.updateByPrimaryKeySelective(student)<1,"修改失败");


    }


    /**删除 删除操作 */
    @Transactional(propagation = Propagation.REQUIRED)
    public void  removeIed(Integer [] id){

        AssertUtil.isTrue(id==null || id.length==0,"请选择数据");


        //是否删除成功


        AssertUtil.isTrue(studentMapper.deleteBatch(id)!=id.length,"删除失败");

    }



}
