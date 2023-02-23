package com.xxxx.cispc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.cispc.base.BaseService;
import com.xxxx.cispc.dao.IdentityMapper;
import com.xxxx.cispc.dao.TableMapper;
import com.xxxx.cispc.query.TableQuery;
import com.xxxx.cispc.utils.AssertUtil;
import com.xxxx.cispc.vo.Identity;
import com.xxxx.cispc.vo.Table;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * table表格展示(业务处理层)
 */
@Service
public class TableService extends BaseService<Table,Integer> {

    @Resource
    private TableMapper tableMapper;
    @Resource
    private IdentityMapper identityMapper;

    /**
     * 多条件查询table数据
     * @param tableQuery 查询对象
     */
    public Map<String,Object> queryTableList(TableQuery tableQuery){
        //初始化分页数据
        PageHelper.startPage(tableQuery.getPage(),tableQuery.getLimit());
        //通过条件查询数据列表
        List<Table> tables = tableMapper.selectByParams(tableQuery);
        //开始分页
        PageInfo<Table> pageInfo=new PageInfo<>(tables);
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
     * 添加表格数据
     * @param table 参数对象
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addTable(Table table){
        //1.参数的校验
        checkParams(table.getUserName(),table.getSex(),table.getCity(),table.getIdentity(),table.getPhone(),table.getEmail());
        //2.对参数进行校验并设置默认值
        AssertUtil.isTrue(tableMapper.queryTableByName(table.getUserName())!=null,"用户名称已存在!");
        //3.参数的设置校验
        settingParams(table);
        //4.设置默认值
        table.setCreateDate(new Date()); //创建时间
        table.setUpdateDate(new Date()); //修改时间
        table.setScore(0); //评分
        table.setExperience(0); //积分
        table.setWealth(0); //财富
        //4.调用添加方法,添加数据
        AssertUtil.isTrue(tableMapper.addTable(table)<1,"记录添加失败!");
    }

    /**
     * 更新表格数据
     * @param table 参数对象
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateTable(Table table){
        //1.参数的校验
        checkParams(table.getUserName(),table.getSex(),table.getCity(),table.getIdentity(),table.getPhone(),table.getEmail());
        //2.判断用户名是否重复-通过用户名查询对象
        Table tempTable=tableMapper.queryTableByName(table.getUserName());
        //判断判断查询到的对象是否是当前对象
        AssertUtil.isTrue(!tempTable.getId().equals(table.getId()),"用户名称已存在!");
        //3.参数的设置校验
        settingParams(table);

        //3.设置参数的默认值
        table.setUpdateDate(new Date());
        //4.调用更新方法,更新数据
        AssertUtil.isTrue(tableMapper.updateByPrimaryKeySelective(table)<1,"记录更新失败!");
    }


    /**
     * 删除表格数据
     * @param tableIds 要删除的记录id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteTable(Integer[] tableIds){
        //1.进行参数校验
        AssertUtil.isTrue(tableIds==null,"请选择要删除的记录!");
        //2.执行删除(判断受影响的行数是否与传入的id个数相得)
        AssertUtil.isTrue(tableMapper.deleteBatch(tableIds)!=tableIds.length,"记录删除失败!");
    }

    /**
     * 参数的设置校验与规范
     * @param table 要校验的table对象
     */
    private void settingParams(Table table) {
        //性别校验(不是男或女就设置为保密)
        table.setSex((table.getSex().equals("女") || table.getSex().equals("男"))?table.getSex():"保密");
        //通过id查询设置用户所选择的身份
        table.setIdentity(identityMapper.selectByPrimaryKey(Integer.parseInt(table.getIdentity())).getNameIdentity());
        //对个性签名进行校验(为填写则设置默认值)
        table.setSign(table.getSign()!=null && !table.getSign().equals("") ?table.getSign():"该用户暂未填写个性签名!");
    }



    /**
     * 参数的非空校验
     * @param userName 用户名称
     * @param sex 用户性别
     * @param city 所在城市
     * @param identity 用户身份
     * @param phone 联系方式
     * @param email 用户邮箱
     */
    private void checkParams(String userName, String sex, String city, String identity, String phone, String email) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名称不可以为空!");
        AssertUtil.isTrue(StringUtils.isBlank(sex),"用户性别不可以为空!");
        AssertUtil.isTrue(StringUtils.isBlank(city),"所在城市不可以为空!");
        AssertUtil.isTrue(StringUtils.isBlank(identity),"请选择用户身份!");
        AssertUtil.isTrue(StringUtils.isBlank(phone),"联系方式不可以为空!");
        AssertUtil.isTrue(StringUtils.isBlank(email),"用户邮箱不可以为空!");
    }


    /**
     * 通过tableId查询记录
     * @param request tableId
     * @param tableId 查询到的table对象
     */
    public void queryTableById(HttpServletRequest request, Integer tableId){
        //根据记录id查询当前记录
        Table table=tableMapper.selectByPrimaryKey(tableId);
        //得到记录中的身份,通过身份名称查询id
        Identity identity=identityMapper.queryIdentityByName(table.getIdentity());
        //将身份对应的id存到table对象中
        table.setIdentity(identity.getId().toString());
        //将查询到的记录存放到请求域中

        System.out.println(table);

        request.setAttribute("table",table);
        //返回视图名称
    }
}
