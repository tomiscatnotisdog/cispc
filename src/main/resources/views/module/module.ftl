<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <#include "../common.ftl">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="../css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <#--搜索栏-->
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">资源名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="moduleName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">资源作者</label>
                            <div class="layui-input-inline">
                                <input type="text" name="originator" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="button" class="layui-btn layui-btn-primary" lay-filter="data-search-btn" lay-submit><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <#--头部工具栏-->
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="add">添加资源</button>
                <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del">移除资源</button>
            </div>
        </script>
        <#--表格-->
        <table class="layui-hide" id="currentModuleId" lay-filter="currentModuleFilter"></table>
        <#--行工具栏-->
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit">更新</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">移除</a>
        </script>
    </div>
</div>
<script src="../lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="../js/module/module.js"></script>
</body>
</html>