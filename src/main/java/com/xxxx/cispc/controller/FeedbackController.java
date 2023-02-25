package com.xxxx.cispc.controller;

import com.xxxx.cispc.base.BaseController;
import com.xxxx.cispc.base.ResultInfo;
import com.xxxx.cispc.query.FeedbackQuery;
import com.xxxx.cispc.service.FeedbackService;
import com.xxxx.cispc.vo.Feedback;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("feedback")
public class FeedbackController extends BaseController {
    @Resource
    private FeedbackService feedbackService;
    //多条件查询
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object>queryFeedbackByParams(FeedbackQuery feedbackQuery){
        return feedbackService.queryFeedbackByParams(feedbackQuery);

    }
    //进入页面
    @RequestMapping("index")
    public String index(){
        return "feedback/feedback";
    }
    //添加操作
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addFeedback(Feedback feedback){
        //调用Service层的添加方法
        feedbackService.addFeedback(feedback);
        return success("数据添加成功");
    }
    //更新操作
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateFeedback(Feedback feedback){
        //调用Service层的添加方法
        feedbackService.updateFeedback(feedback);
        return success("数据更新成功");
    }
    //删除操作
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteFeedback(Integer[] ids){
        //调用service的方法
        feedbackService.deleteFeedback(ids);
        return success("删除成功！！");

    }

    //进入添加he 修改数据的页面
    @RequestMapping("toFeedbackPage")
    public String toFeedbackPage(Integer feedbackId,HttpServletRequest request){

        //判断id是否为空
        if (feedbackId!=null){
            //通过id查询数据
            Feedback feedback=feedbackService.selectByPrimaryKey(feedbackId);
            request.setAttribute("feedback",feedback);

        }

        return "feedback/add_update";
    }



}
