package com.xxxx.cispc.dao;

import com.xxxx.cispc.base.BaseMapper;
import com.xxxx.cispc.vo.MakeFrend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MakeFrendMapper extends BaseMapper<MakeFrend,Integer> {


    MakeFrend selectByMakeFrend(String activityName);

    MakeFrend selectByUserNameAndPhone(@Param("createMan") String createMan, @Param("createManPhone") String createManPhone);

    Integer deleteFrendById(Integer id);
}