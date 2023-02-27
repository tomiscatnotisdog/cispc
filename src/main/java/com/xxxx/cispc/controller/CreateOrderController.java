package com.xxxx.cispc.controller;

import com.xxxx.cispc.base.BaseController;
import com.xxxx.cispc.base.ResultInfo;
import com.xxxx.cispc.query.MyReceiveQuery;
import com.xxxx.cispc.query.RunningQuery;
import com.xxxx.cispc.service.CreateOrderService;
import com.xxxx.cispc.vo.CreateOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Map;

@RequestMapping("/create_order")
@Controller
public class CreateOrderController extends BaseController {
    @Resource
    private CreateOrderService createOrderService;

    /**
     * 进入所有跑腿订单页面
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "createOrder/all_create_order";
    }


    /**
     * 分页查询所有跑腿订单
     * @return
     */
    @RequestMapping("allOrderList")
    @ResponseBody
    public Map<String,Object> queryRunningByParams(RunningQuery runningQuery){

        return createOrderService.queryByParamsForTable(runningQuery);
    }

    /**
     * 进入”我接收的“页面
     * @return
     */
    @RequestMapping("myCreateOrder")
    public String myCreateOrder(){
        return "createOrder/create_order";
    }

    /**
     * 进入添加/修改数据页面
     * @param createOrderId
     * @param request
     * @return
     */
    @RequestMapping("toUpdatePage")
    public String toUpdatePage(Integer createOrderId, HttpServletRequest request) {

        // 判断createOrderId是否为空
        if (createOrderId != null) {
            // 通过ID查询数据
            CreateOrder createOrder = createOrderService.selectByPrimaryKey(createOrderId);
            // 将数据设置到请求域中
            request.setAttribute("createOrder",createOrder);
        }

        return "createOrder/add_update";
    }
    /**
     * 进入发起跑腿订单详情页面
     * @return
     */
    @RequestMapping("createRunningOrder")
    public String createOrder(){

        return "createOrder/add";
    }
    /**
     * 分页查询我发起的跑腿订单
     * @param runningQuery
     * @return
     */
    @RequestMapping("myList")
    @ResponseBody
    public Map<String,Object> selectMyCreate(RunningQuery runningQuery,HttpServletRequest request){

        //从cookie中拿到当前登录用户的信息
        Cookie[] cookie = request.getCookies();
        String decode = null;
        for (Cookie name: cookie) {
            if (name.getName().equals("trueName")){
                decode = URLDecoder.decode(name.getValue());
            };
        }
        /*System.out.println("======================"+ decode);*/
        String trueName = decode;
        /*System.out.println("================"+trueName);*/
        runningQuery.setTrueName(trueName);

        return createOrderService.queryMyCreate(runningQuery);
    }


    /**
     * 发起跑腿服务请求
     * @param createOrder
     * @return
     */
    @PostMapping("create")
    @ResponseBody
    public ResultInfo createRunning(CreateOrder createOrder){
        createOrderService.createRunning(createOrder);
        return success("跑腿请求发起成功！");
    }

    /**
     * 更新跑腿服务请求
     * @param createOrder
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateOrder(CreateOrder createOrder){
        createOrderService.updateOrder(createOrder);
        return success("订单信息更新成功！");
    }

    /**
     * 撤销跑腿订单
     * @param ids
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteOrder(String[] ids, HttpServletRequest request){


        createOrderService.deleteOrder(ids,request);
        return success("订单撤销成功！");
    }



    /**
     * 接收跑腿订单
     * @param ids
     * @return
     */
    @RequestMapping("receiveOrder")
    @ResponseBody
    public ResultInfo receiveOrder(String[] ids, HttpServletRequest request){

        createOrderService.receiveOrder(ids,request);

        return success("跑腿订单接收成功！");
    }

    /**
     * 完成跑腿订单
     * @param ids
     * @return
     */
    @RequestMapping("completeOrder")
    @ResponseBody
    public ResultInfo completeOrder(String[] ids, HttpServletRequest request){

        createOrderService.completeOrder(ids,request);
        return success("跑腿订单接收成功！");
    }

    /**
     * 进入接收订单页面
     * @return
     */
    @RequestMapping("receive")
    public String receive(){
        return "receiveOrder/receive_order";
    }

    /**
     * 分页查询我接收的跑腿订单
     * @param myReceiveQuery
     * @return
     */
    @RequestMapping("receiveOrderList")
    @ResponseBody
    public Map<String,Object> receiveOrderList(MyReceiveQuery myReceiveQuery,HttpServletRequest request){
        //从cookie中拿到当前登录用户的信息
        Cookie[] cookie = request.getCookies();
        String decode = null;
        for (Cookie name: cookie) {
            if (name.getName().equals("trueName")){
                decode = URLDecoder.decode(name.getValue());
            };
        }
        System.out.println("======================"+ decode);
        String trueName = decode;
        System.out.println("================"+trueName);
        myReceiveQuery.setTrueName(trueName);
        return createOrderService.queryMyReceive(myReceiveQuery);
    }

}
