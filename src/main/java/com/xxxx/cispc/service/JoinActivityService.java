package com.xxxx.cispc.service;


import com.xxxx.cispc.base.BaseService;
import com.xxxx.cispc.dao.JoinActivityMapper;
import com.xxxx.cispc.dao.MakeFrendMapper;
import com.xxxx.cispc.model.MyjoinModel;
import com.xxxx.cispc.utils.AssertUtil;
import com.xxxx.cispc.vo.JoinActivity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class JoinActivityService extends BaseService<JoinActivity,Integer> {

    @Resource
    private JoinActivityMapper joinActivityMapper;

    @Resource
    private MakeFrendMapper makeFrendMapper;


    @Transactional(propagation = Propagation.REQUIRED)
    public void JoinFrend(Integer id, String activityName, String createMan, String createManPhone, String joinMan) {
        AssertUtil.isTrue(joinActivityMapper.selectJoinedPeopleById(id) > makeFrendMapper.selectByPrimaryKey(id).getMaxNumbers(),"参加人数已经达到上线 ");
        AssertUtil.isTrue(joinMan.equals(createMan) ,"您是发起者不需要参加");
        AssertUtil.isTrue(joinActivityMapper.selectByJoinMan(joinMan,id) != null,"您已经参加，请勿重复提交");
        AssertUtil.isTrue(id == null || makeFrendMapper.selectByPrimaryKey(id) == null,"待加入记录不存在" );
        AssertUtil.isTrue(StringUtils.isBlank(activityName),"活动名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(createManPhone),"手机号码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(createMan) ,"创建人不能为空");
        JoinActivity joinActivity = new JoinActivity();
        //  赋予值
        joinActivity.setJoinDate(new Date());
        joinActivity.setActivityName(activityName);
        joinActivity.setCreateMan(createMan);
        joinActivity.setMakeFrendId(id);
        joinActivity.setCreateManPhone(createManPhone);
        joinActivity.setJoinMan(joinMan);
        AssertUtil.isTrue(joinActivityMapper.insertSelective(joinActivity) != 1 ,"添加成功");


    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void updateComment(String comment,Integer id,String joinMan) {
        AssertUtil.isTrue(id == null,"待评论记录不存在");
        MyjoinModel temp = joinActivityMapper.selectByMakeFrendId(id,joinMan);
        AssertUtil.isTrue(temp == null,"待评论记录不存在");
        AssertUtil.isTrue(temp.getComment() != null ,"已经评论过不能重复评论");
        AssertUtil.isTrue(joinActivityMapper.updateComment(comment,id,joinMan) !=1 ,"更新评论失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void frendunjoin(Integer id,String joinMan) {


        AssertUtil.isTrue(joinActivityMapper.frendunjoin(id,joinMan) != 1,"取消失败");
    }
}
