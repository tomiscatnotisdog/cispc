<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>基本资料</title>
    <#include "../common.ftl">
    <style>
        .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-form layuimini-form">
            <input name="id" type="hidden" value="${(user.id)!}"/>
            <div class="layui-form-item">
                <label class="layui-form-label required">管理账号</label>
                <div class="layui-input-block">
                    <input type="text" name="truename" lay-verify="required" readonly="readonly" class="layui-input" value="${(user.trueName)!}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label  required">手机</label>
                <div class="layui-input-block">
                    <input type="phone" name="phon"  placeholder="请输入手机"  value="${(user.phone)!}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label  required">学号</label>
                <div class="layui-input-block">
                    <input type="userNumber" name="usernumber"   placeholder="请输入学号"  value="${(user.userNumber)!}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label  required">昵称</label>
                <div class="layui-input-block">
                    <input type="text" name="nickname"  placeholder="请输入昵称"  value="${(user.nickName)!}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">个人简介</label>
                <div class="layui-input-block">
                    <input type="text" name="personalprofile" value="${(user.personalProfile)!}" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="hidden" name="id" value="${(user.id)!}" >
                    <button class="layui-btn" lay-submit lay-filter="saveBtn">确认保存</button>
                </div>

            </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/js/user/setting.js"></script>
</body>
</html>