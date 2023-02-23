package com.xxxx.cispc.dao;

import com.xxxx.cispc.base.BaseMapper;
import com.xxxx.cispc.vo.Table;

public interface TableMapper extends BaseMapper<Table, Integer> {

    /**
     * 通过用户名称查询数据
     * @return 返回查询到的table对象
     */
    Table queryTableByName(String userName);

    /**
     * 添加用户记录
     * @param table 要添加的用户对象
     * @return 返回受影响的行数
     */
    int addTable(Table table);
}