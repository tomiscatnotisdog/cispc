<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>cispc后台管理</title>
    <#include "common.ftl">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="lib/layui-v2.5.5/css/layui.css" media="all">
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        html, body {width: 100%;height: 100%;overflow: hidden}
        /*登陆页面背景颜色*/
        body {background: #00b7ee;}
        body:after {content:'';background-repeat:no-repeat;background-size:cover;-webkit-filter:blur(3px);-moz-filter:blur(3px);-o-filter:blur(3px);-ms-filter:blur(3px);filter:blur(3px);position:absolute;top:0;left:0;right:0;bottom:0;z-index:-1;}
        .layui-container {width: 100%;height: 100%;overflow: hidden}
        .admin-login-background {width:360px;height:300px;position:absolute;left:50%;top:40%;margin-left:-180px;margin-top:-100px;}
        .logo-title {text-align:center;letter-spacing:2px;padding:14px 0;}
        /*输入框标题字体颜色*/
        .logo-title h1 {color:#00b7ee;font-size:25px;font-weight:bold;}
        .login-form {background-color:#fff;border:1px solid #fff;border-radius:3px;padding:14px 20px;box-shadow:0 0 8px #eeeeee;}
        .login-form .layui-form-item {position:relative;}
        .login-form .layui-form-item label {position:absolute;left:1px;top:1px;width:38px;line-height:36px;text-align:center;color:#d2d2d2;}
        .login-form .layui-form-item input {padding-left:36px;}
        .captcha {width:60%;display:inline-block;}
        .captcha-img {display:inline-block;width:34%;float:right;}
        .captcha-img img {height:34px;border:1px solid #e6e6e6;height:36px;width:100%;}
    </style>
</head>
<body>
<div class="layui-container">
    <div class="admin-login-background">
        <div class="layui-form login-form">
            <form class="layui-form" action="">
                <div class="layui-form-item logo-title">
                    <h1>Cispc综合服务平台</h1>
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-usernumber" for="usernumber"></label>
                    <input type="text" name="usernumber" lay-verify="required|usernumber"  placeholder="学号" autocomplete="off" class="layui-input" value=""><#--20202020-->
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-pwd" for="pwd"></label>
                    <input type=password name="pwd" lay-verify="required|pwd" placeholder="密码" autocomplete="off" class="layui-input" value=""><#--123456-->
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-capt" for="capt"></label>
                    <input type="text" name="capt" lay-verify="required|capt" placeholder="图形验证码" autocomplete="off" class="layui-input verification captcha" value=""><#--xszG-->
                    <div class="captcha-img">
                        <img id="captchaPic" src="images/captcha.jpg">
                    </div>
                </div>
                <div class="layui-form-item">
                    <input type="checkbox" name="rememberMe" id="rememberMe" value="true" lay-skin="primary" title="记住密码">
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-fluid layui-btn-normal" lay-submit="" lay-filter="login">登 入</button>
                </div>

            </form>
            <div>

                <a href="${ctx}/user/register" class="layui-btn layui-btn-fluid layui-btn-warm">注册</a>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/js/index.js" charset="utf-8"></script>
</body>
</html>