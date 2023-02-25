<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(makeFrends.id)!}"/>
 <#--   <input name="man" type="hidden" id="assignManId" value="${(makeFrend.createMan)!}"/>--><#---->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">发起人</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="createMan" id="createMan"  value="${(makeFrends.createMan)!}" placeholder="请输入您真实姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">活动地点</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="place" id="place"  value="${(makeFrends.place)!}" placeholder="请输入最大人数">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">活动类型</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                    name="type" id="type" value="${(makeFrends.type)!}" placeholder="请输入活动地点">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">开始时间</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                    name="createDate" id="createDate" lay-verify="required"  value="${(makeFrends.createDate?string('yyyy-MM-dd HH:mm:ss'))!}"
                   placeholder="请输入活动类型">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">结束时间</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="complateDate"  id="complateDate" value="${(makeFrends.complateDate?string('yyyy-MM-dd HH:mm:ss'))!}"  placeholder="请输入结束时间">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">是否结束</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                    name="isComplate" id="isComplate" value="${(makeFrends.isComplate)!}"  placeholder="是否结束">
        </div>
    </div>
    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">结束时间</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                    name="complateDate" id="complateDate" value="${(makeFrend.complateDate)!}" placeholder="请输入结束时间">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">活动名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="activityName" id="activityName" value="${(makeFrend.activityName)!}" placeholder="请输入活动名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">发起人电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="createManPhone" id="createManPhone" value="${(makeFrend.isCom)!}" placeholder="请输入电话">
        </div>
    </div>-->
   <#-- <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">机会描述</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入机会描述信息" name="description" class="layui-textarea">${(makeFrend.activityName)!}</textarea>
        </div>
    </div>-->
<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
        <#--<label class="layui-form-label">指派人</label>
        <div class="layui-input-block">
            <select name="assignMan" id="assignMan">
                <option value="">请选择</option>
            </select>
        </div>-->
     <#--   <label class="layui-form-label">机会描述</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入机会描述信息" name="description" class="layui-textarea">${(makeFrend.createManPhone)!}</textarea>
        </div>
    </div>-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="updatecomment">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/createupdate/update.js"></script>
</body>
</html>