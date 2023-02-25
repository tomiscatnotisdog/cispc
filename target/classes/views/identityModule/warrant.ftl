<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:90%;">
            <#--权限id-->
            <input name="id" type="hidden" value="${(identity.id)!}"/>

            <#--系统提示-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">提示信息</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input 系统提示" disabled
                           lay-verify="" name="系统提示" id="系统提示"   placeholder="每次授权将清空已有权限,请谨慎选择">
                </div>
            </div>
            <#--用户身份-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">用户身份</label>
                <div class="layui-input-block">
                    <select name="identityName" id="identity"  >
                        <option disabled value="" selected>请选择要授权的角色</option>
                    </select>
                </div>
            </div>
            <#--系统提示-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">提示信息</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input 系统提示" disabled
                           lay-verify="" name="系统提示" id="系统提示"   placeholder="请在下列的选项中选择要授权的资源">
                </div>
            </div>



            <div class="layui-form-item">
                <label class="layui-form-label">资源权限</label>
                <#--<div class="layui-input-block" id="checkbox">
                    <input type="checkbox" name="code" title="综合管理"><div class="layui-unselect layui-form-checkbox layui-form-checked"><span>综合管理</span><i class="layui-icon layui-icon-ok"></i></div>
                    <input type="checkbox" name="code" title="交友互动"><div class="layui-unselect layui-form-checkbox"><span>交友互动</span><i class="layui-icon layui-icon-ok"></i></div>
                    <input type="checkbox" name="code" title="游戏管理"><div class="layui-unselect layui-form-checkbox"><span>游戏管理</span><i class="layui-icon layui-icon-ok"></i></div>

                    <input type='checkbox' name='code' value='1' title="综合管理"><div class='layui-unselect layui-form-checkbox '><span>综合管理</span><i class='layui-icon layui-icon-ok'></i></div>
                    <input type='checkbox' name='code' value='1' title="校园管理"><div class='layui-unselect layui-form-checkbox '><span>校园管理</span><i class='layui-icon layui-icon-ok'></i></div>
                    <input type='checkbox' name='code' value='1' title="评价反馈"><div class='layui-unselect layui-form-checkbox '><span>评价反馈</span><i class='layui-icon layui-icon-ok'></i></div>
                </div>-->
                    <div class="layui-input-block">
                        <input type="checkbox" name="code" title="综 合管 理" value="1">
                        <input type="checkbox" name="code" title="交 友活 动" value="2">
                        <input type="checkbox" name="code" title="游 戏管 理" value="3">
                        <input type="checkbox" name="code" title="校 园管 理" value="4">
                        <input type="checkbox" name="code" title="跑 腿服 务" value="5">
                        <input type="checkbox" name="code" title="评 价反 馈" value="6">
                    </div>
            </div>














            <br/>
            <div class="layui-form-item layui-row layui-col-xs12" >
                <div class="layui-input-block">  <#--lay-submit="" layui提交校验-->
                    <button <#--type="button"--> class="layui-btn layui-btn-lg" lay-submit="" lay-filter="warrant">确认</button>
                    <button type="button" class="layui-btn layui-btn-lg layui-btn-normal" id="qwe">取消</button>
                </div>
            </div>
        </form>

    <script type="text/javascript" src="${ctx}/js/identityModule/warrant.js"></script>
    </body>
</html>