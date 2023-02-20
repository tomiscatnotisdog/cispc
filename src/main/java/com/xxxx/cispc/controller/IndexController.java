package com.xxxx.cispc.controller;


import com.xxxx.cispc.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {

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
        return "main";
    }

}
