package com.xxxx.cispc.controller;

import com.xxxx.cispc.base.BaseController;
import com.xxxx.cispc.service.IdentityService;
import com.xxxx.cispc.vo.Identity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("identity")
public class IdentityController extends BaseController {

    @Resource
    private IdentityService identityService;

    @RequestMapping("allIdentity")
    @ResponseBody
    public List<Identity>  queryAllIdentity(){
        return identityService.queryAllIdentity();
    }
}
