package com.xxxx.cispc.dao;

import com.xxxx.cispc.base.BaseMapper;
import com.xxxx.cispc.base.BaseQuery;
import com.xxxx.cispc.query.MyGameQuery;
import com.xxxx.cispc.vo.Game;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;


public interface GameMapper extends BaseMapper<Game,Integer> {

    List<Map<String, Object>> queryAllStater(Integer userId);
}