package com.xxxx.cispc.dao;

import com.xxxx.cispc.base.BaseMapper;
import com.xxxx.cispc.model.IdentityModuleModel;
import com.xxxx.cispc.query.IdentityModuleQuery;
import com.xxxx.cispc.vo.IdentityModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IdentityModuleMapper extends BaseMapper<IdentityModule,Integer> {

    /**
     * 多条件查询权限信息
     * @param identityModuleQuery (查询对象) 身份名称
     * @return 查询到的结果
     */
    List<IdentityModuleModel> queryIdentityByParams(IdentityModuleQuery identityModuleQuery);


    /**
     * 根据角色名称查询权限记录
     * @param identityName
     * @return
     */
    int queryIdentityModuleByIdentityName(String identityName);


    /**
     * 根据角色id,删除对应的权限记录
     * @param identityId
     * @return
     */
    int deleteIdentityModuleByIdentityId(String identityId);


    /**
     * 进行角色授权
     * @param identityId
     * @param code
     * @return
     */
    int warrant(@Param(value = "identityId") Integer identityId, @Param(value = "code") String code);

    /**
     * 通过用户身份查询所拥有的权限码
     * @param identity
     * @return
     */
    List<Integer> queryCodeByIdentityName(String identity);
}