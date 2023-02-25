package com.xxxx.cispc.dao;

import com.xxxx.cispc.base.BaseMapper;
import com.xxxx.cispc.vo.Identity;

public interface IdentityMapper extends BaseMapper<Identity,Integer> {

    /**
     * 根据角色名称查询角色
     * @param nameIdentity
     * @return
     */
    Identity queryIdentityByName(String nameIdentity);

    /**
     * 添加角色记录
     * @param identity
     * @return
     */
    int addIdentity(Identity identity);


    /**
     * 批量删除角色记录,返回受影响的行数
     * @param identityIds
     * @return
     */
    int deleteIdentity(Integer[] identityIds);
}