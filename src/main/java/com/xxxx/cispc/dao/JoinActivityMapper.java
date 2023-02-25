package com.xxxx.cispc.dao;

import com.xxxx.cispc.base.BaseMapper;
import com.xxxx.cispc.model.MyjoinModel;
import com.xxxx.cispc.vo.JoinActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface JoinActivityMapper extends BaseMapper<JoinActivity,Integer> {

    int updateComment(@Param("comment") String comment, @Param("id") Integer id, @Param("joinMan") String joinMan);

    MyjoinModel selectByMakeFrendId(@Param("id") Integer id, @Param("joinMan") String joinMan);

    int frendunjoin(@Param("id") Integer id, @Param("joinMan") String joinMan);

    JoinActivity selectByJoinMan(@Param("joinMan") String joinMan, @Param("id") Integer id);

    Integer selectJoinedPeopleById(Integer id);
}