package com.xxxx.cispc.dao;

import com.xxxx.cispc.base.BaseMapper;
import com.xxxx.cispc.vo.Module;

import java.util.List;

public interface ModuleMapper extends BaseMapper<Module,Integer> {

    /**
     * 根据资源名称查询资源对象
     * @param moduleName 资源名称
     * @return 查询到的资源
     */
    Module queryModuleByName(String moduleName);

    /**
     * 批量移除资源
     * @param moduleIds 待移除的资源id
     * @return 返回受影响的行数
     */
    int deleteModule(Integer[] moduleIds);

    /**
     * 通过所属资源查询资源记录(当前后台写死为0,'main')
     * @return main页面下的资源
     */
    List<Module> queryModulebyParentId();

    /**
     * 根据资源码查询资源对象
     * @param code 待查询到资源码
     * @return 查询到的资源对象
     */
    Module queryModuleByCode(String code);

    /**
     * 查询所有的一级资源(父id为0,且不是main)
     * @return 返回符合要求的资源信息
     */
    List<Module> queryModuleIsOne();

    /**
     * 通过f父id查询对应的资源码集合
     * @param c
     * @return
     */
    List<String> queryCodebyparentId(Integer c);
}