package com.xxxx.cispc.controller;


import com.xxxx.cispc.base.BaseController;
import com.xxxx.cispc.service.UserService;
import com.xxxx.cispc.utils.LoginUserUtil;
import com.xxxx.cispc.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController extends BaseController {
    @Resource
    private UserService userService;

    /**
     * 系统登录⻚
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "index-1";
    }

    // 系统界⾯欢迎⻚
    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 后端管理主⻚⾯
     *
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request) {
        // 通过⼯具类，从cookie中获取userId
        Integer id = LoginUserUtil.releaseUserIdFromCookie(request);

        // 调⽤对应Service层的⽅法，通过userId主键查询⽤户对象
        User user = (User) userService.selectByPrimaryKey(id);

        //权限相关操作-宦吉田
        //1.通过用户账户获取身份,取得资源码(学号或工号)
        List<Integer> codes=userService.queryCode(user.getUserNumber());
        System.out.println(codes);
        //2.将资源码放到请求域中
        request.getSession().setAttribute("codes",codes);


        // 将⽤户对象设置到request作⽤域中
        request.getSession().setAttribute("user",user);

        //通过当前用户ID查询当前登录用户拥有的资源列表
       /* List<String> permissions = permissionService.queryUserHasRoleHasPermissionByUserId(userId);
        request.getSession().setAttribute("permissions",permissions);*/

        return "main";
    }



}
