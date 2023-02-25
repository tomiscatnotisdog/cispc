package com.xxxx.cispc.dao;

import com.xxxx.cispc.base.BaseMapper;
import com.xxxx.cispc.vo.StuXueHao;

public interface StuXueHaoMapper extends BaseMapper<StuXueHao,Integer> {
    //判断姓名已存在
    public Integer queryRoleByRoleName(String studentname);
    //判断学号已存在
    public Integer queryRoleByXuehao(String student);

}