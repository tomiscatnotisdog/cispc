package com.xxxx.cispc.dao;

import com.xxxx.cispc.base.BaseMapper;
import com.xxxx.cispc.vo.Identity;

/**
 * 身份表identity
 */
public interface IdentityMapper extends BaseMapper<Identity, Integer> {

    /**
     * 通过名称查询身份id
     *
     * @param nameIdentity 身份名称
     * @return identity对象
     */
    Identity queryIdentityByName(String nameIdentity);

}