package com.xxxx.cispc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.cispc.base.BaseService;
import com.xxxx.cispc.dao.ModuleMapper;
import com.xxxx.cispc.query.ModuleQuery;
import com.xxxx.cispc.utils.AssertUtil;
import com.xxxx.cispc.vo.Module;
import com.xxxx.cispc.vo.Table;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModuleService extends BaseService<Module,Integer> {

    //依赖注入
    @Resource
    private ModuleMapper moduleMapper;


    /**
     * 多条件查询资源
     * @param moduleQuery 资源查询对象
     * @return 表格所需格式的map集合
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> queryTableList(ModuleQuery moduleQuery) {
        //初始化分页数据
        PageHelper.startPage(moduleQuery.getPage(),moduleQuery.getLimit());
        //通过条件查询数据列表
        List<Module> modules = moduleMapper.selectByParams(moduleQuery);
        //开始分页
        PageInfo<Module> pageInfo=new PageInfo<>(modules);
        //新建一个Map集合
        Map<String,Object> map=new HashMap<>();
        //将查询结果存放到map集合中
        map.put("code",0); //状态码
        map.put("msg","success"); //提示信息
        map.put("count",pageInfo.getTotal()); //数据的个数
        map.put("data",pageInfo.getList()); //数据
        //将得到的结果进行返回
        return map;
    }

    /**
     * 添加资源信息
     * @param module 待添加的资源对象
     */
    public void addTable(Module module) {
        //1.参数非空校验
        checkNullParams(module.getModuleName(),module.getCode(),module.getOriginator(),module.getParentId(),module.getUrl());
        //2.资源冲突校验
        AssertUtil.isTrue(moduleMapper.queryModuleByName(module.getModuleName())!=null,"资源名称已经存在!");
        checkParams(module.getParentId()); //查看所属资源是否存在
        //3.设置默认值(创建时间,更新时间,是否有效)
        module.setCreateDate(new Date());
        module.setUpdateDate(new Date());
        module.setIsValid(1);
        //4.执行添加操作
        AssertUtil.isTrue(moduleMapper.insertSelective(module)<1,"资源添加失败!");
    }


    /**
     * 更新资源信息
     * @param module 待更新的资源对象
     */
    public void updateModule(Module module) {
        //1.参数非空校验
        checkNullParams(module.getModuleName(),module.getCode(),module.getOriginator(),module.getParentId(),module.getUrl());
        //2.资源冲突校验
        //通过资源名称查询资源对象
        Module tempmodule=moduleMapper.queryModuleByName(module.getModuleName());
        //如果存在,查看是否是自己
        if (tempmodule!=null){
            AssertUtil.isTrue(tempmodule.getId()!=module.getId(),"资源名称已存在!");
        }
        checkParams(module.getParentId());
        //3.设置默认值(创建时间,更新时间,是否有效)
        module.setUpdateDate(new Date());
        //4.执行更新操作
        AssertUtil.isTrue(moduleMapper.updateByPrimaryKeySelective(module)<1,"资源更新失败!");
    }

    /**
     * 资源冲突校验
     * @param parentId 所属资源
     */
    private void checkParams(String parentId) {
        //查看所属资源是否存在
        AssertUtil.isTrue(moduleMapper.queryModuleByCode(parentId)==null,"所属资源不存在!");
    }

    /**
     * 参数非空校验
     * @param moduleName 资源名称
     * @param code 资源码
     * @param originator 资源作者
     * @param parentId 所属资源
     * @param url 资源路径
     */
    private void checkNullParams(String moduleName, String code, String originator, String parentId, String url) {
        AssertUtil.isTrue(StringUtils.isBlank(moduleName),"资源名称已存在!");
        AssertUtil.isTrue(StringUtils.isBlank(code),"资源码不可以为空!");
        AssertUtil.isTrue(StringUtils.isBlank(originator),"资源作者不可以为空!!");
        AssertUtil.isTrue(parentId==null,"所属资源不可以为空!");
        AssertUtil.isTrue(StringUtils.isBlank(url),"资源路径不可以为空!");
    }


    /**
     * 资源的批量移除
     * @param moduleIds 待移除的资源id
     */
    public void deleteMoudle(Integer[] moduleIds) {
        //1.进行非空校验
        AssertUtil.isTrue(moduleIds==null || moduleIds.length==0,"请选择要移除的资源id!");
        //2.执行移除操作
        AssertUtil.isTrue(moduleMapper.deleteModule(moduleIds)!=moduleIds.length,"资源移除失败,请重试!");
    }

    /**
     * 通过所属资源查询资源记录(当前后台写死为0,'main')
     * @return main页面下的资源
     */
    public List<Module> queryModulebyParentId() {
        return moduleMapper.queryModulebyParentId();
    }

    /**
     * 查询所有的一级资源(父id为0,且不是main)
     * @return 返回符合要求的资源信息
     */
    @RequestMapping("moduleIsOne")
    public List<Module> queryModuleIsOne(){
        return moduleMapper.queryModuleIsOne();
    }
}
