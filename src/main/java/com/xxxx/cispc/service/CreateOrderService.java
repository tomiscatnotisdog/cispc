package com.xxxx.cispc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.cispc.base.BaseService;
import com.xxxx.cispc.dao.CreateOrderMapper;
import com.xxxx.cispc.model.CreateModel;
import com.xxxx.cispc.query.MyReceiveQuery;
import com.xxxx.cispc.query.RunningQuery;
import com.xxxx.cispc.utils.AssertUtil;
import com.xxxx.cispc.utils.PhoneUtil;
import com.xxxx.cispc.vo.CreateOrder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CreateOrderService extends BaseService<CreateOrder,Integer> {
    @Resource
    private CreateOrderMapper createOrderMapper;




    /**
     * 查询我发起的跑腿订单，分页
     * @param runningQuery
     * @return
     */
    public Map<String,Object> queryMyCreate(RunningQuery runningQuery){
        Map<String,Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(runningQuery.getPage(),runningQuery.getLimit());
        //得到对应分页对象
        PageInfo<CreateOrder> pageInfo =new PageInfo<>(createOrderMapper.selectMyCreate(runningQuery));
        //设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        //设置分页好的列表
        map.put("data",pageInfo.getList());

        return map;
    }


    /**
     * 发起跑腿服务请求
     * @param createOrder
     */
    public void createRunning(CreateOrder createOrder) {
        //参数校验
        checkRunningParams(createOrder);
        //判断是否有效,默认有效
        createOrder.setIsValid(1);
        //设置服务发起时订单状态
        createOrder.setIsComplete(1);
        //服务内容发起时间
        createOrder.setCreateDate(new Date());
        //插入数据，判断受影响的行数
        AssertUtil.isTrue(createOrderMapper.insertSelective(createOrder) !=1,"服务请求发起失败！");
    }

    /**
     * 更新跑腿服务的请求
     * @param createOrder
     */
    public void updateOrder(CreateOrder createOrder) {
        //参数校验
        AssertUtil.isTrue(null == createOrder.getId() && createOrderMapper.selectByPrimaryKey(createOrder.getId()) == null,"请求数据异常，再检查一遍吧！");
        checkRunningParams(createOrder);
        //修改时间
        createOrder.setCreateDate(new Date());
        //执行更新，判断受影响的行数
        AssertUtil.isTrue(createOrderMapper.updateByPrimaryKeySelective(createOrder) != 1,"订单信息更新失败！");
    }


    /**
     * 参数校验的方法
     * @param createOrder
     */
    private void checkRunningParams(CreateOrder createOrder) {

        //参数校验

        //发起人   非空
        AssertUtil.isTrue(StringUtils.isBlank(createOrder.getCreateMan()),"发起人姓名不能为空！");
        //创建人手机号  校验
        AssertUtil.isTrue(StringUtils.isBlank(createOrder.getCreateManPhone()),"手机号不能为空！");
        AssertUtil.isTrue(!PhoneUtil.isMobile(createOrder.getCreateManPhone()) ,"手机号码格式不正确！");
        //服务内容  非空
        AssertUtil.isTrue(StringUtils.isBlank(createOrder.getContext()),"服务内容不能为空！");
        System.out.println(createOrder.getCreateManLocation());
        //目的地  非空
        AssertUtil.isTrue(null == createOrder.getCreateManLocation(),"目的地不能为空！");

        //学工号  非空
        AssertUtil.isTrue(null == createOrder.getStUserNumber(),"学工号不能为空！");
    }

    /**
     * 撤销订单
     *createOrder
     * @param ids
     */
    public void deleteOrder(String[] ids,HttpServletRequest request) {
        AssertUtil.isTrue(null == ids || ids.length < 1,"待撤销订单不存在！");
        //从cookie中拿到当前登录用户的信息
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        String userNumber = "";
        for (int i = 0; i < cookies.length; i++) {
            String cookieKey = cookies[i].getName();
            if (cookieKey.equals("userNumber")){
                userNumber = cookies[i].getValue();
            }
        }
        CreateModel createModel = createOrderMapper.selectReceiveManByUserNumber(Integer.valueOf(userNumber));
        createModel.setStReceiveUserNumber(Integer.valueOf(userNumber));

        for (String id : ids) {

            CreateOrder createOrder = createOrderMapper.selectByPrimaryKey(Integer.valueOf(id));
            createOrder.setIsComplete(0);
            createOrder.setIsValid(0);
            //执行更新操作
            AssertUtil.isTrue(createOrderMapper.updateByPrimaryKeySelective(createOrder) != 1,"订单撤销失败！");
        }


    }

    /**
     * 接收跑腿订单
     * @param ids
     */
    public void receiveOrder(String[] ids,HttpServletRequest request) {

        AssertUtil.isTrue(null == ids || ids.length < 1,"待接收订单不存在！");
        //从cookie中拿到当前登录用户的信息
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        String userNumber = "";
        String trueName = "";
        for (int i = 0; i < cookies.length; i++) {
            String cookieKey = cookies[i].getName();
            if (cookieKey.equals("userNumber")){
                userNumber = cookies[i].getValue();
            }else if(cookieKey.equals("trueName")){
                trueName = cookies[i].getValue();
            }
        }
        CreateModel createModel = createOrderMapper.selectReceiveManByUserNumber(Integer.valueOf(userNumber));
        createModel.setStReceiveUserNumber(Integer.valueOf(userNumber));
        createModel.setTrueName(trueName);

        for (String id : ids) {
            CreateOrder createOrder = createOrderMapper.selectByPrimaryKey(Integer.valueOf(id));

            //当前用户不能接收自己的订单
            if(createModel.getTrueName() == createOrder.getReceiveMan()){
                continue;

            }else {
                createOrder.setReceiveDate(new Date());
                createOrder.setReceiveMan(createModel.getReceiveMan());
                createOrder.setReceiveManPhone(createModel.getReceiveManPhone());
                createOrder.setIsComplete(2);
                //执行更新操作
                AssertUtil.isTrue(createOrderMapper.updateByPrimaryKeySelective(createOrder) != 1,"跑腿订单接收失败！");
            }


        }





    }

    /**
     * 分页查询我接收的跑腿订单
     * @return
     */
    public Map<String,Object> queryMyReceive(MyReceiveQuery myReceiveQuery) {
        Map<String,Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(myReceiveQuery.getPage(),myReceiveQuery.getLimit());
        //得到对应分页对象
        PageInfo<CreateOrder> pageInfo =new PageInfo<>(createOrderMapper.selectMyReceive(myReceiveQuery));
        //设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        //设置分页好的列表
        map.put("data",pageInfo.getList());

        return map;
    }

    /**
     * 完成跑腿订单
     * @param ids
     */
    public void completeOrder(String[] ids,HttpServletRequest request) {
        AssertUtil.isTrue(null == ids || ids.length < 1,"该订单不存在！");
        //从cookie中拿到当前登录用户的信息
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        String userNumber = "";
        for (int i = 0; i < cookies.length; i++) {
            String cookieKey = cookies[i].getName();
            if (cookieKey.equals("userNumber")){
                userNumber = cookies[i].getValue();
            }
        }
        CreateModel createModel = createOrderMapper.selectReceiveManByUserNumber(Integer.valueOf(userNumber));
        createModel.setStReceiveUserNumber(Integer.valueOf(userNumber));

        for (String id : ids) {

            CreateOrder createOrder = createOrderMapper.selectByPrimaryKey(Integer.valueOf(id));
            createOrder.setIsComplete(3);
            createOrder.setCompleteDate(new Date());
            //执行更新操作
            AssertUtil.isTrue(createOrderMapper.updateByPrimaryKeySelective(createOrder) != 1,"跑腿订单未完成！");
        }

    }
}
