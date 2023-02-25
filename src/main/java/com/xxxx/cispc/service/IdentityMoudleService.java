package com.xxxx.cispc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.cispc.base.BaseService;
import com.xxxx.cispc.dao.IdentityMapper;
import com.xxxx.cispc.dao.IdentityModuleMapper;
import com.xxxx.cispc.dao.ModuleMapper;
import com.xxxx.cispc.model.IdentityModuleModel;
import com.xxxx.cispc.query.IdentityModuleQuery;
import com.xxxx.cispc.query.IdentityQuery;
import com.xxxx.cispc.utils.AssertUtil;
import com.xxxx.cispc.vo.Identity;
import com.xxxx.cispc.vo.IdentityModule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IdentityMoudleService extends BaseService<IdentityModule,Integer> {

    @Resource
    private IdentityModuleMapper identityModuleMapper;
    @Resource
    private IdentityMapper identityMapper;
    @Resource
    private ModuleMapper moduleMapper;

    /**
     * 多条件查询权限详情
     * @param identityModuleQuery 查询对象
     * @return  前端需要的map集合
     */
    public Map<String, Object> queryIdentityByParams(IdentityModuleQuery identityModuleQuery) {

        //初始化分页数据
        PageHelper.startPage(identityModuleQuery.getPage(),identityModuleQuery.getLimit());
        //通过条件查询数据列表
        List<IdentityModuleModel> identityModules=identityModuleMapper.queryIdentityByParams(identityModuleQuery);
        //开始分页
        PageInfo<IdentityModuleModel> pageInfo=new PageInfo<>(identityModules);
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
     * 进行身份授权
     * @param identityName 角色名称
     * @param code
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void warrant(String identityName, String[] code) {
        //1.参数非空判断
        AssertUtil.isTrue(StringUtils.isBlank(identityName),"请选择需要授权的角色!");
        //通过角色名称,查看对应的角色是否存在
        AssertUtil.isTrue(identityMapper.queryIdentityByName(identityName)==null,"所授权的角色不存在!");
        //要授权的资源不能为空
        AssertUtil.isTrue(code==null || code.length==0,"请选择要授予的资源!");
        //通过角色名称,查询角色id
        Integer identityId = identityMapper.queryIdentityByName(identityName).getId();
        //2.通过角色名称,查看已拥有的权限记录
        int count=identityModuleMapper.queryIdentityModuleByIdentityName(identityName);
        //3.判断是否存在权限,如果存在,将其全部移除

        System.out.println(identityName);
        System.out.println(identityId);

        if (count!=0){
            //通过角色id删除对应的权限记录
            AssertUtil.isTrue(identityModuleMapper.deleteIdentityModuleByIdentityId(Integer.toString(identityId))<count,"角色授权失败,请重试!");
        }

        //4.通过权限码,绑定权限(本环境:绑定使用code和parent_id是code的资源)
        for (String c:code){
            //通过父id查询对应的子资源的资源码
            List<String> codeList=moduleMapper.queryCodebyparentId(Integer.parseInt(c));
            //将父id也放入资源码集合中
            codeList.add(c);
            //遍历资源码集合,依次添加权限记录
            for (int i=0;i<codeList.size();i++){
                AssertUtil.isTrue(identityModuleMapper.warrant(identityId,codeList.get(i))!=1,"授权失败,请重试!");
            }
        }

    }

    /**
     * 移除权限
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteIdentityModule(Integer id) {
        //1.判断id是否为空
        AssertUtil.isTrue(id==null,"请选择要移除的资源权限!");
        //2.判断id对应的资源是否存在
        AssertUtil.isTrue(identityModuleMapper.selectByPrimaryKey(id)==null,"待移除的权限记录不存在!");
        //3.调用删除方法,移除对应的记录
        AssertUtil.isTrue(identityModuleMapper.deleteByPrimaryKey(id)<1,"权限移除失败!");
    }

    /**
     * 通过用户身份查询所拥有的权限码
     * @param identity
     * @return
     */
    public List<Integer> queryCodeByIdentityName(String identity) {
        //1.非空校验
        AssertUtil.isTrue(StringUtils.isBlank(identity),"检测到当前用户身份不明,即为空!");
        //2.查看当前用户是否存在(即合法)
        AssertUtil.isTrue(identityMapper.queryIdentityByName(identity)==null,"当前用户不合法,未在数据库中找到!");
        //3.通过用户身份在数据中查询相应的权限码
        return identityModuleMapper.queryCodeByIdentityName(identity);
    }
}
