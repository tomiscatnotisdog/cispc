
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
                            <label class="layui-form-label">价格</label>
                            <div class="layui-input-inline">
                                <input type="text" name="gamePrice" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">游戏说明</label>
                            <div class="layui-input-inline">
                                <input type="text" name="gameProfile" autocomplete="off" class="layui-input">
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

    </div>
</div>

<script type="text/javascript" src="${ctx}/js/game/game.js"></script>

</body>
</html>