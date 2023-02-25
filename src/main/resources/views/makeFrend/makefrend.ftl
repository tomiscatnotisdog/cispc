<!DOCTYPE html>
<html>
<head>
    <title>交友机会管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="activityName"
                           class="layui-input
					searchVal" placeholder="活动名称" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="createMan" class="layui-input
					searchVal" placeholder="发起人" />
                </div>

                <div class="layui-input-inline">
                    <input type="text" name="maxNumbers" class="layui-input
					searchVal" placeholder="最大人数" />
                </div>
             <#--   <div class="layui-input-inline">
                    <select name="state"  id="state">
                        <option value="" >分配状态</option>
                        <option value="0">未分配</option>
                        <option value="1" >已分配</option>
                    </select>
                </div>-->
                <#--	<#if permissions?seq_contains("101001")>-->
                <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
                <#--	</#if>-->
            </div>
        </form>
    </blockquote>
    <table id="frendList" class="layui-table"  lay-filter="frend"></table>


    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <#--	<#if permissions?seq_contains("101002")>-->
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                发起
            </a>
            <#--</#if>
            <#if permissions?seq_contains("101003")>-->
       <#--
            <#--</#if>-->
<#--            头部工具篮子-->
            <#--<a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除
            </a>&ndash;&gt;-->
        </div>
    </script>


    <!--操作-->
    <script id="JoinFrendBar" type="text/html">
        <#--	<#if permissions?seq_contains("101004")>-->
        <a class="layui-btn layui-btn-xs" id="join" lay-event="join">参加</a>
        <#--	</#if>
            <#if permissions?seq_contains("101003")>-->

        <#--</#if>-->
    </script>

</form>
<script type="text/javascript" src="${ctx}/js/makeFrend/make.frend.js"></script>

</body>
</html>