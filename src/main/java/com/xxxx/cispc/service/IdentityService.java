package com.xxxx.cispc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.cispc.base.BaseService;
import com.xxxx.cispc.dao.IdentityMapper;
import com.xxxx.cispc.query.IdentityQuery;
import com.xxxx.cispc.utils.AssertUtil;
import com.xxxx.cispc.vo.Identity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class IdentityService extends BaseService<Identity,Integer> {

    @Resource
    private IdentityMapper identityMapper;

    /**
     * 查询所有的身份信息
     * @return 查询的identity对象
     */
    public List<Identity> queryAllIdentity(){
        return  identityMapper.selectByParams(new IdentityQuery());

    }

    /**
     * 多条件查询角色信息
     * @param identityQuery 查询对象
     * @return 结果
     */
    public Map<String, Object> queryIdentityByParams(IdentityQuery identityQuery) {
        //初始化分页数据
        PageHelper.startPage(identityQuery.getPage(),identityQuery.getLimit());
        //通过条件查询数据列表
        List<Identity> identities=identityMapper.selectByParams(identityQuery);
        //开始分页
        PageInfo<Identity> pageInfo=new PageInfo<>(identities);
        //创建一个map集合,将前台所需的数据格式放入
        Map<String,Object> map=new HashMap<>();
        //将所需的参数设入
        map.put("code",0);
        map.put("msg","查询成功!");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        //将设置好的map集合返回
        return map;
    }

    /**
     * 添加角色信息
     * @param identity 要添加的角色对象
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addTable(Identity identity) {
        //1.参数校验(角色名称非空且唯一)
        AssertUtil.isTrue(StringUtils.isBlank(identity.getNameIdentity()),"角色名称不可以为空!");
        //判断名称是否唯一
        AssertUtil.isTrue(identityMapper.queryIdentityByName(identity.getNameIdentity())!=null,"角色名称已存在!");
        //2.设置默认值(和空值转换:如果未设置备注则备注就是角色名称)
        identity.setRemarks(StringUtils.isBlank(identity.getRemarks())?identity.getNameIdentity():identity.getRemarks());
        //创建时间,更新时间,是否有效
        identity.setCreateDate(new Date());
        identity.setUpdateDate(new Date());
        identity.setIsValid(1);
        //3.执行添加操作
        AssertUtil.isTrue(identityMapper.addIdentity(identity)<1,"角色添加失败!");
    }

    /**
     * 更新角色记录
     * @param identity 待更新的角色对象
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateIdentity(Identity identity) {
        //1.参数校验(角色名称非空且唯一)
        AssertUtil.isTrue(StringUtils.isBlank(identity.getNameIdentity()),"角色名称不可以为空!");
        //判断名称是否唯一(通过角色名称查询角色对象)
        Identity tempIdentity=identityMapper.queryIdentityByName(identity.getNameIdentity());
        //判断是否存在对应角色名称的对象,如果存在是否是自己
        if (tempIdentity!=null){
            AssertUtil.isTrue(tempIdentity.getId()!=identity.getId(),"角色名称已存在!");
        }
        //2.设置默认值(和空值转换:如果未设置备注则备注就是角色名称)
        identity.setRemarks(StringUtils.isBlank(identity.getRemarks())?identity.getNameIdentity():identity.getRemarks());
        //更新时间
        identity.setUpdateDate(new Date());
        //3.执行修改操作
        AssertUtil.isTrue(identityMapper.updateByPrimaryKeySelective(identity)<1,"角色修改失败!");

    }

    /**
     * 批量删除角色记录
     * @param identityIds 待删除的角色记录id数组
     */
    public void deleteTable(Integer[] identityIds) {
        //1.进行参数校验
        AssertUtil.isTrue(identityIds==null||identityIds.length==0,"请选择要删除的记录!");
        //2.执行删除(判断受影响的行数是否与传入的id个数相得)
        AssertUtil.isTrue(identityMapper.deleteIdentity(identityIds)!=identityIds.length,"记录删除失败!");
    }
}
