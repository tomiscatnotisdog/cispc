package com.xxxx.cispc.service;

import com.xxxx.cispc.base.BaseService;
import com.xxxx.cispc.dao.IdentityMapper;
import com.xxxx.cispc.query.IdentityQuery;
import com.xxxx.cispc.vo.Identity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class IdentityService extends BaseService<Identity,Integer> {

    @Resource
    private IdentityMapper identityMapper;

    /**
     * 查询所有的身份信息
     * @return
     */
    public List<Identity> queryAllIdentity(){
        return  identityMapper.selectByParams(new IdentityQuery());

    }
}
