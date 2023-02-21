package com.xxxx.cispc.controller;

import com.xxxx.cispc.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*快捷入口的控制层*/
@Controller
public class pageController extends BaseController {

    /**
     * 404页面
     * @return
     */
    @RequestMapping("404")
    public String s0s(){
        return "page/404";
    }


    /**
     * 图标展示
     * @return
     */
    @RequestMapping("icon")
    public String icon(){
        return "page/icon";
    }

    /**
     * 表单示例
     * @return
     */
    @RequestMapping("table")
    public String table(){
        return "page/table";
    }

    /**
     * 系统设置
     * @return
     */
    @RequestMapping("setting")
    public String setting(){
        return "page/setting";
    }

    /**
     * 表单示例
     * @return
     */
    @RequestMapping("form")
    public String form(){
        return "page/form";
    }

    /**
     * 菜单管理
     * @return
     */
    @RequestMapping("menu")
    public String menu(){
        return "page/menu";
    }

    /**
     * 按钮示例
     * @return
     */
    @RequestMapping("button")
    public String button(){
        return "page/button";
    }

    /**
     * 弹出层
     * @return
     */
    @RequestMapping("layer")
    public String layer(){
        return "page/layer";
    }
}
