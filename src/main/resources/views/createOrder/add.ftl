<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(createOrder.id)!}"/>
    <input name="createOrderId" type="hidden" value="${createOrderId!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">发起人姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="createMan" id="createMan"  value="${(createOrder.createMan)!}"  placeholder="请输入发起人姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="createManPhone" id="createManPhone"  value="${(createOrder.createManPhone)!}" placeholder="请输入联系电话">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">跑腿内容</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="context" id="context"  value="${(createOrder.context)!}" placeholder="请输入跑腿内容">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">目的地</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="createManLocation" id="createManLocation"  value="${(createOrder.createManLocation)!}" placeholder="请输入目的地">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">学工号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input stUserNumber"
                   lay-verify="required" name="stUserNumber" id="stUserNumber"  value="${(createOrder.stUserNumber?c)!}" placeholder="请输入学工号">
        </div>
    </div>
<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">发起时间</label>-->
<#--        <div class="layui-input-block">-->
<#--            <#if (createOrder.createDate)??>-->
<#--                <input type="text" class="layui-input userName"-->
<#--                   lay-verify="required" name="createDate" id="createDate" value="${(createOrder.createDate?string('yyyy-MM-dd HH:mm:ss'))!}" placeholder="请输入时间">-->
<#--                <#else>-->
<#--                <input type="text" class="layui-input userName"-->
<#--                   lay-verify="required" name="createDate" id="createDate"  placeholder="请输入计划项时间">-->
<#--            </#if>-->

<#--        </div>-->
<#--    </div>-->
<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">执行效果</label>-->
<#--        <div class="layui-input-block">-->
<#--            <input type="text" class="layui-input userEmail"-->
<#--                   lay-verify="required" name="exeAffect" value="${(createOrder.exeAffect)!}"-->
<#--                   id="exeAffect" placeholder="请输入执行效果">-->
<#--        </div>-->
<#--    </div>-->


    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateOrder">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/createOrder/add.update.js"></script>
</body>
</html>