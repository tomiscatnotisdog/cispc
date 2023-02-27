package com.xxxx.cispc.dao;

import com.xxxx.cispc.base.BaseMapper;
import com.xxxx.cispc.model.CreateModel;
import com.xxxx.cispc.vo.CreateOrder;


public interface CreateOrderMapper extends BaseMapper<CreateOrder,Integer> {




    CreateModel selectReceiveManByUserNumber(Integer userNumber);


}