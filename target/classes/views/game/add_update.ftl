<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
    <form class="layui-form" style="width:80%;">
        <input name="id" type="hidden" value="${(game.id)!}"/>

        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">游戏名称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input userEmail"
                       lay-verify="gameName" name="gameName" value="${(game.gameName)!}"
                       id="email"
                       placeholder="请输入游戏名称">
            </div>
        </div>

        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">价格</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input userEmail"
                       lay-verify="gamePrice" name="gamePrice" value="${(game.gamePrice?c)!}" placeholder="请输入价格">
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">游戏说明</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input userEmail"
                       lay-verify="gameProfile" name="gameProfile" value="${(game.gameProfile)!}" placeholder="请输入说明">
            </div>
        </div>

        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">保证金</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input userEmail"
                       lay-verify="bond" name="bond" value="${(game.bond?c)!}" placeholder="请输入保证金">
            </div>
        </div>

        <input id="staterId" type="hidden" value="${(game.stater)!}"/>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">请选择</label>
            <div class="layui-input-block">
                <select name="stater" id="stater">
                    <option value=""></option>
                </select>
            </div>
        </div>

        <br/>
        <div class="layui-form-item layui-row layui-col-xs12">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-lg" lay-submit=""
                        lay-filter="addOrUpdateGame">确认
                </button>
                <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
            </div>
        </div>
    </form>

<script type="text/javascript" src="${ctx}/js/game/add.update.js"></script>

</body>
</html>