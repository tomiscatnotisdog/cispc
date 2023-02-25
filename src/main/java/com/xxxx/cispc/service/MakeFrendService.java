package com.xxxx.cispc.service;

import com.xxxx.cispc.base.BaseService;
import com.xxxx.cispc.dao.MakeFrendMapper;
import com.xxxx.cispc.utils.AssertUtil;
import com.xxxx.cispc.utils.PhoneUtil;
import com.xxxx.cispc.vo.MakeFrend;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class MakeFrendService extends BaseService<MakeFrend,Integer> {

    @Resource
    private MakeFrendMapper makeFrendMapper;

    /*
    *   1 参数验证
    *           活动id 名称不能重复
    *           字段全部不能空
    *            活动最大人数不能超过30人
    *             电话号码要验证
    *             发起人要存在
    *             开始时间 要大于当前时间  小于 结束时间  开始时间大于时间类型 两个小时
    *           根据 名称 不能 存在
    *           根据创建人的真实姓名 和 电话号码 在user表中查询 存在
    *
    * */

    @Transactional(propagation = Propagation.REQUIRED)
    public void addFrend(MakeFrend makeFrend) {

        System.out.println(makeFrend);
        checkParams(makeFrend);

        AssertUtil.isTrue(makeFrend.getMaxNumbers()> 30,"活动最大人数不能超过30");

        MakeFrend temp = makeFrendMapper.selectByMakeFrend(makeFrend.getActivityName());

        AssertUtil.isTrue(temp != null ,"该活动已经存在");

        makeFrend.setIsValid(1);

        AssertUtil.isTrue(makeFrendMapper.insertSelective(makeFrend) !=1,"添加失败");

    }

    private void checkParams(MakeFrend makeFrend) {
        AssertUtil.isTrue(makeFrend.getActivityName() == null && makeFrend.getActivityName() == "","活动名称不能为空");

        AssertUtil.isTrue(makeFrend.getCreateDate() == null ,"活动开始时间不能为空");


        AssertUtil.isTrue(makeFrend.getCreateMan() == null && makeFrend.getCreateMan() == ""
                && makeFrendMapper.selectByUserNameAndPhone(makeFrend.getCreateMan(),makeFrend.getCreateManPhone()) != null,"活动为空 或者创建人不存在");

        AssertUtil.isTrue(PhoneUtil.isMobile(makeFrend.getCreateManPhone()),"手机格式不正确");

        AssertUtil.isTrue(makeFrend.getComplateDate() == null && makeFrend.getActivityName() == "","最大人数必须设置");

        AssertUtil.isTrue(makeFrend.getMaxNumbers() == null && makeFrend.getActivityName() == "","最大人数必须设置");

        AssertUtil.isTrue(makeFrend.getPlace() == null && makeFrend.getPlace() == "","活动地点不能为空");

        AssertUtil.isTrue(makeFrend.getType()== null && makeFrend.getType() == "","活动类型不能为空");


        System.out.println(makeFrend.getComplateDate());
        System.out.println(makeFrend.getComplateDate());
        long start = makeFrend.getCreateDate().getTime();
        System.out.println(start);

        long end = makeFrend.getComplateDate().getTime();
        System.out.println(end);
        System.out.println(end - start);
        AssertUtil.isTrue(end - start < 3600,"活动时间不能低于两个小时");

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteFrendById(Integer id) {
//        通过id删除  删除的发起人 应该等于 自己才可以

        AssertUtil.isTrue(id == null,"待删除记录不存在");

        AssertUtil.isTrue(makeFrendMapper.deleteFrendById(id) != 1 ,"删除失败");
        System.out.println("============================"+makeFrendMapper.selectByPrimaryKey(id));
    }


    /*
    *
    *
    *
    * */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateFrend(MakeFrend makeFrend,Integer id) {
        AssertUtil.isTrue(id == null || makeFrendMapper.selectByPrimaryKey(id) == null,"待更新记录不存在");
        AssertUtil.isTrue(makeFrendMapper.updateByPrimaryKeySelective(makeFrend) != 1 ,"删除失败");
    }
}
