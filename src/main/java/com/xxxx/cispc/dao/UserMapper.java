package com.xxxx.cispc.dao;

import com.xxxx.cispc.base.BaseMapper;
import com.xxxx.cispc.vo.User;


public interface UserMapper extends BaseMapper<User,Integer> {
    //根据学号，查询学号对象
    User queryUserByName(String userNumber);
 


    User selectByUserNumber(String userNumber);

    int delectuserNumber(String userNumber);
}