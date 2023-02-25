
<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">游戏名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="gameName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">状态</label>
                            <div class="layui-input-inline">
                                <select name="stater"  id="stater">
                                    <option value="" >状态</option>
                                    <option value="0">未接单</option>
                                    <option value="1" >进行中</option>
                                    <option value="2">已完成</option>
                                    <option value="3" >失败</option>
                                </select>
                            </div>
                        </div>

                        <a class="layui-btn search_btn" data-type="reload">
                            <i class="layui-icon">&#xe615;</i>
                            搜索
                        </a>
                    </div>
                </form>
            </div>
        </fieldset>

        <table class="layui-hide" id="gameList" lay-filter="games"></table>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm data-add-btn" lay-event="add"> 添加游戏 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除游戏 </button>
            </div>
        </script>

        <script type="text/html" id="gameListBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>
<script type="text/javascript" src="${ctx}/js/game/my.game.js"></script>
</body>
</html>