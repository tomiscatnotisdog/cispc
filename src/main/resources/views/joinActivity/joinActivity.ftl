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

             <#--   <div class="layui-input-inline">
                    <input type="text" name="maxNumbers" class="layui-input
					searchVal" placeholder="最大人数" />
                </div>-->
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
    <#--    <div class="layui-btn-container">
            &lt;#&ndash;	<#if permissions?seq_contains("101002")>&ndash;&gt;
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                评价
            </a>

            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                投诉
            </a>
        </div>-->
    </script>


    <!--操作-->
    <script id="JoinFrendBar" type="text/html">
        <#--	<#if permissions?seq_contains("101004")>-->
        <a class="layui-btn layui-btn-xs" id="join" lay-event="comment">评价</a>


     <#--   <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">投诉</a>-->

        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="unjoin">取消参加</a>
    </script>

</form>
<script type="text/javascript" src="${ctx}/js/joinActivity/joinActivity.js"></script>

</body>
</html>