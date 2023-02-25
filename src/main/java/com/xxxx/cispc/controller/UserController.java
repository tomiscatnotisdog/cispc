package com.xxxx.cispc.controller;

import com.xxxx.cispc.base.BaseController;
import com.xxxx.cispc.base.ResultInfo;
import com.xxxx.cispc.exceptions.ParamsException;
import com.xxxx.cispc.model.UserModel;
import com.xxxx.cispc.service.IdentityMoudleService;
import com.xxxx.cispc.service.UserService;
import com.xxxx.cispc.utils.LoginUserUtil;
import com.xxxx.cispc.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("user")
@Controller
public class UserController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private IdentityMoudleService identityMoudleService; //权限资源

    /**⽤户登录*/
    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String userNumber, String password , String captcha, String nickName, String trueName, String identity, HttpSession session){





        ResultInfo resultInfo = new ResultInfo();
        UserModel userModel = userService.userLogin(userNumber,password,captcha,nickName,trueName);
       /* Cookie[] cookies = request.getCookies();
        //String userId = String.valueOf(cookies[1]);
        System.out.println(cookies[0].getValue());
        System.out.println(cookies[1].getValue());
        System.out.println(cookies[2].getValue());
        System.out.println(cookies[3].getValue());
        System.out.println(cookies[4].getValue());*/
        resultInfo.setResult(userModel);
        return resultInfo;
    }

    /**修改*/
    @PostMapping("updateUserPwd")
    @ResponseBody
    public ResultInfo updateUserPwd(HttpServletRequest request,String oldPwd, String newPwd, String confirmPwd){
        ResultInfo resultInfo = new ResultInfo();
        /*Cookie[] cookies = request.getCookies();
        System.out.println(cookies[1].getValue());
        System.out.println(cookies[2].getValue());
        System.out.println(cookies[3].getValue());
        System.out.println(cookies[4].getValue());*/
        //try {
            Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
            userService.updateUserPwd(userId,oldPwd,newPwd,confirmPwd);
       /* }catch (ParamsException p){
            resultInfo.setCode(p.getCode());
            resultInfo.setMsg(p.getMsg());
            p.printStackTrace();
        }catch (Exception e){
            resultInfo.setCode(500);
            resultInfo.setMsg("密码修改失败");
        }*/
        return resultInfo;
    }
    /**
     * 进入修改密码页面
     */
    @RequestMapping("toPasswordPage")
    public String toPasswordPage(){

        return "user/password";
    }




    /**
     * 基本资料
     * */
    @RequestMapping("toSettingPage")
    public String toSettingPage(){
        return "user/setting";
    }

    @PostMapping("updateSetting")
    @ResponseBody
    public ResultInfo updateSetting(User user){
        ResultInfo resultInfo = new ResultInfo();
        User user1 = userService.updateSetting(user);
        resultInfo.setResult(user1);
        return resultInfo;
    }

    /**注册账户*/
    @PostMapping("insertregister")
    @ResponseBody
    public ResultInfo insertregister(User user){
        ResultInfo resultInfo = new ResultInfo();
        User user1 = userService.insertregister(user);
        resultInfo.setResult(user1);
        return resultInfo;
    }

    @RequestMapping("register")
    public String register(){

        return "user/register";
    }

    /**注销用户*/
    @PostMapping("deletecancellation")
    @ResponseBody
    public ResultInfo deletecancellation(String userNumber){

        userService.deletecancellation(userNumber);

        return success("用户注销成功");
    }
}
