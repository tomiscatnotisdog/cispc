package com.xxxx.cispc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.cispc.base.BaseService;
import com.xxxx.cispc.dao.StuXueHaoMapper;
import com.xxxx.cispc.query.StuIdnameQuery;
import com.xxxx.cispc.utils.AssertUtil;
import com.xxxx.cispc.vo.StuXueHao;
import com.xxxx.cispc.vo.Student;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudenIdService extends BaseService<StuXueHao,Integer> {

    @Resource
    private StuXueHaoMapper stuXueHaoMapper;


    public Map<String,Object> queryStudentID(StuIdnameQuery stuIdnameQuery  ){
        //初始化分页数据
        PageHelper.startPage(stuIdnameQuery.getPage(),stuIdnameQuery.getLimit());
        //条件查询
//
        List<StuXueHao> stuxues = stuXueHaoMapper.selectByParams(stuIdnameQuery);
        //开始分页
        PageInfo<StuXueHao> plist=new PageInfo<>(stuxues);
        //实列化Map
        Map<String,Object>map=new HashMap<>();
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        return map;
    }


    public void saveSaleChance(StuXueHao stuXueHao){
        AssertUtil.isTrue(StringUtils.isBlank(stuXueHao.getStudentname()),"请输入名称!");
        AssertUtil.isTrue(0 !=stuXueHaoMapper.queryRoleByRoleName(stuXueHao.getStudentname()),"该名字已存在!");
        AssertUtil.isTrue(0 !=stuXueHaoMapper.queryRoleByXuehao(stuXueHao.getStudent()),"该学号已存在!");
        checkStudentt(stuXueHao.getStudentname(), stuXueHao.getStudent(), stuXueHao.getDepartment(), stuXueHao.getClasnam(), stuXueHao.getSpecialized(),stuXueHao.getReason(),stuXueHao.getApprover(),stuXueHao.getRefuse(),stuXueHao.getDevresult());
        AssertUtil.isTrue(insertSelective(stuXueHao)<1,"用户记录添加失败!");


    }





    private void checkStudentt(String studentname, String student, String department, String clasnam, String specialized, String reason, String approver, String refuse, String devresult) {
        AssertUtil.isTrue(StringUtils.isBlank(studentname),"请输入名字!");
        AssertUtil.isTrue(StringUtils.isBlank(student),"请输入学号!");
        AssertUtil.isTrue(StringUtils.isBlank(department),"请输入系部!");
        AssertUtil.isTrue(StringUtils.isBlank(specialized),"请输入专业!");
        AssertUtil.isTrue(StringUtils.isBlank(clasnam),"请输入班级!");
        AssertUtil.isTrue(StringUtils.isBlank(reason),"请输入审核人!");
        AssertUtil.isTrue(StringUtils.isBlank(approver),"请输入请假理由!");


        AssertUtil.isTrue(StringUtils.isBlank(devresult),"请输入状态!");


    }
    //   更新操作
    public  void changeUser(StuXueHao stuXueHao){
        //判断用户ID是否存在


        StuXueHao temp = stuXueHaoMapper.selectByPrimaryKey(stuXueHao.getId());
        AssertUtil.isTrue(temp==null,"更新记录不存在");
       // AssertUtil.isTrue(0 !=stuXueHaoMapper.queryRoleByRoleName(stuXueHao.getStudentname()),"该名字已存在!");
      // AssertUtil.isTrue(0 !=stuXueHaoMapper.queryRoleByXuehao(stuXueHao.getStudent()),"该学号已存在!");
        checkStudentt(stuXueHao.getStudentname(), stuXueHao.getStudent(), stuXueHao.getDepartment(), stuXueHao.getClasnam(), stuXueHao.getSpecialized(),stuXueHao.getReason(),stuXueHao.getApprover(),stuXueHao.getRefuse(),stuXueHao.getDevresult());
        AssertUtil.isTrue(stuXueHaoMapper.updateByPrimaryKeySelective(stuXueHao)<1,"修改失败");



    }




    /**删除 删除操作 */
    @Transactional(propagation = Propagation.REQUIRED)
    public void  removeIed(Integer [] id){

        AssertUtil.isTrue(id==null || id.length==0,"请选择数据");
        //是否删除成功
        AssertUtil.isTrue(stuXueHaoMapper.deleteBatch(id)!=id.length,"删除失败");

    }



}
