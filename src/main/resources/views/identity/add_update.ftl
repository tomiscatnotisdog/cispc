<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:90%;">
            <#--角色ID-->
            <input name="id" type="hidden" value="${(identity.id)!}"/>
            <#--角色名称-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">角色名称</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input nameIdentity"
                           lay-verify="required" name="nameIdentity" id="nameIdentity"  value="${(identity.nameIdentity)!}" placeholder="请输入角色名称">
                </div>
            </div>

            <#--角色备注-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">角色备注</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input remarks"
                           lay-verify="" name="remarks" id="remarks"  value="${(identity.remarks)!}" placeholder="请输入备注信息">
                </div>
            </div>

            <br/>
            <div class="layui-form-item layui-row layui-col-xs12" >
                <div class="layui-input-block">  <#--lay-submit="" layui提交校验-->
                    <button <#--type="button"--> class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateIdentity">确认</button>
                    <button type="button" class="layui-btn layui-btn-lg layui-btn-normal" id="qwe">取消</button>
                </div>
            </div>
        </form>

    <script type="text/javascript" src="${ctx}/js/identity/add_update.js"></script>
    </body>
</html>