<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id" value="${(feedback.id)!}">
    <#--<input type="hidden" name="man" value="${(saleChance.assignMan)!}">-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">真实姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="trueName" id="trueName" value="${(feedback.trueName)!}"
                   placeholder="请输入真实姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">昵称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="nickName" id="nickName" placeholder="请输入你的昵称" value="${(feedback.nickName)!}">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="phone" name="phone" id="phone" placeholder="请输入联系电话"
                   value="${(feedback.phone)!}">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">内容</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="content" id="content" placeholder="请输入内容" value="${(feedback.content)!}">
        </div>
    </div>
    <#--下拉框-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">处理结果</label>
        <div class="layui-input-block">
            <select name="results"  id="results">
                <option value="" >处理状态</option>
                <option value="0">未处理</option>
                <option value="1" >已处理</option>
            </select>
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateFeedback">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>


</form>
<script type="text/javascript" src="${ctx}/js/feedback/add.update.js"></script>
</body>
</html>



















