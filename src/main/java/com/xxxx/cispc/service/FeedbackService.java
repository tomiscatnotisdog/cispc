package com.xxxx.cispc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.cispc.base.BaseService;
import com.xxxx.cispc.dao.FeedbackMapper;
import com.xxxx.cispc.query.FeedbackQuery;
import com.xxxx.cispc.utils.AssertUtil;
import com.xxxx.cispc.utils.PhoneUtil;
import com.xxxx.cispc.vo.Feedback;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequestMapping("feedback")
public class FeedbackService extends BaseService<Feedback,Integer> {
    @Resource
    private FeedbackMapper feedbackMapper;

    //多条件查询
    public Map<String,Object>queryFeedbackByParams(FeedbackQuery feedbackQuery){
        Map<String,Object>map=new HashMap<>();

        //开启分页
        PageHelper.startPage(feedbackQuery.getPage(),feedbackQuery.getLimit());
        //得到对应分页对象
        PageInfo<Feedback> pageInfo=new PageInfo<>(feedbackMapper.selectByParams(feedbackQuery));
        //设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        //设置列表
        map.put("data",pageInfo.getList());


        return map;
    }
    //添加操作

    @Transactional(propagation = Propagation.REQUIRED)
    public void addFeedback(Feedback feedback){
        //一、参数校验
        checkFeedbackParams(feedback.getTrueName(),feedback.getNickName(),feedback.getPhone());
        //二、设置默认值
        //设置有效数据=1时有效
        feedback.setIsValid(1);
        //当前是系统时间
        feedback.setUpdateDate(new Date());
        //三、执行添加操作，返回受影响行数
        AssertUtil.isTrue(feedbackMapper.insertSelective(feedback)!=1,"添加操作失败！！！");

    }
    //更新操作
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateFeedback(Feedback feedback){
        //一、参数校验
        //id 非空
        AssertUtil.isTrue(null==feedback.getId(),"待更新记录不存在！！");
        //查询对象
        Feedback t=feedbackMapper.selectByPrimaryKey(feedback.getId());
        //数据库是否有数据存在
        AssertUtil.isTrue(t==null,"待更新记录不存在！！1");
        checkFeedbackParams(feedback.getNickName(), feedback.getTrueName(), feedback.getPhone());
        //二、设置默认值
        feedback.setUpdateDate(new Date());

        //三、执行更新返回受影响行数
        AssertUtil.isTrue(feedbackMapper.updateByPrimaryKeySelective(feedback)!=1,"更新失败！！！");
    }


    //删除操作
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteFeedback(Integer[] ids){
        //判断id是否为空
        AssertUtil.isTrue(null==ids || ids.length<1,"待删除记录不存在！！！");
        //执行删除操作，判断受影响的行数
        AssertUtil.isTrue(feedbackMapper.deleteBatch(ids)!=ids.length,"删除数据失败！！！");
    }





    //参数校验
    private void checkFeedbackParams(String trueName, String nickName, String phone) {
        //一、1.真实姓名不能够为空
        AssertUtil.isTrue(StringUtils.isBlank(trueName),"真是姓名不能为空！！");
        //2.昵称不能为空
        AssertUtil.isTrue(StringUtils.isBlank(nickName),"昵称不能为空！！");
        //3.电话号码不能为空
        AssertUtil.isTrue(StringUtils.isBlank(phone),"手机号码不能为空！！");
        //4.手机格式不正确
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"手机号格式不对哦！！");

    }


}


















