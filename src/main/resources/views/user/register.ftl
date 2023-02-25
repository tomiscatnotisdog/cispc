<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <#include "../common.ftl">
    <link rel="stylesheet" href="${ctx}/css/register.css" media="all">
</head>
<body>
<div class="layui-container">
    <div class="admin-login-background">
        <div class="layui-form login-form">
            <form class="layui-form" action="">
                <div class="layui-form-item logo-title">
                    <h1> Cispc综合服务-注册 </h1>
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-usernumber" for="usernumber"></label>
                    <input type="text" name="usernumber" lay-verify="required|usernumber"  placeholder="学号" autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-trueName" for="trueName"></label>
                    <input type="text" name="trueName" lay-verify="required|trueName"  placeholder="真实姓名" autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">

                    <label class="layui-icon layui-icon-nickName" for="nickName"></label>
                    <input type="text" name="nickName" lay-verify="required|nickName"  placeholder="昵称" autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-phone" for="phone"></label>
                    <input type="text" name="phone" lay-verify="required|phone"  placeholder="手机号" autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="password"></label>
                    <input type="text" name="password" lay-verify="required|password"  placeholder="密码" autocomplete="off" class="layui-input" value="">
                </div>

                <#--用户身份-->
                <div class="layui-form-item">
                    <select name="identity" id="identity"  >
                        <option disabled value="" selected>请选择用户身份</option>
                        <option  value="学生" >学生</option>
                        <option  value="教师" >教师</option>
                    </select>
                </div>

                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="register">确认注册</button>
                </div>


            </form>
        </div>
    </div>
</div>
<script src="${ctx}/js/register.js" charset="utf-8"></script>
</body>
</html>