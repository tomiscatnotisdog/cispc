<!DOCTYPE html>
<html>
<head>
    <title>评价服务管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="trueName"
                           class="layui-input
					searchVal" placeholder="真实姓名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="nickName" class="layui-input
					searchVal" placeholder="昵称" />
                </div>
                <div class="layui-input-inline">
                    <select name="results"  id="results">
                        <option value="" >处理状态</option>
                        <option value="0">正在处理</option>
                        <option value="1" >已处理</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>
    <table id="feedbackList" class="layui-table"  lay-filter="feedBack"></table>


    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除
            </a>
        </div>
    </script>


    <!--编辑删除操作-->
    <script id="feedbackListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>
<script type="text/javascript" src="${ctx}/js/feedback/feedback.js"></script>

</body>
</html>