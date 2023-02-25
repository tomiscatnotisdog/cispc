<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:90%;">
            <#--资源ID-->
            <input name="id" type="hidden" value="${(module.id)!}"/>
            <#--资源名称-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">资源名称</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input moduleName"
                           lay-verify="required" name="moduleName" id="moduleName"  value="${(module.moduleName)!}" placeholder="请输入资源名称">
                </div>
            </div>

            <#--资源码-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">资源码</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input code"
                           lay-verify="" name="code" id="code"  value="${(module.code)!}" placeholder="请输入资源码">
                </div>
            </div>

            <#--资源作者-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">资源作者</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input originator"
                           lay-verify="" name="originator" id="originator"  value="${(module.originator)!}" placeholder="请输入资源作者">
                </div>
            </div>


            <#--所属资源-->
            <input name="parentId" type="hidden" value="${(module.parentId)!}"/>
            <#--所属资源-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">所属资源</label>
                <div class="layui-input-block">
                    <select name="parentId" id="parentId"  >
                        <option disabled value="" selected>请选择所属资源</option>
                    </select>
                </div>
            </div>

            <#--资源路径-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">资源路径</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input url"
                           lay-verify="" name="url" id="url"  value="${(module.url)!}" placeholder="请输入资源路径">
                </div>
            </div>

            <br/>
            <div class="layui-form-item layui-row layui-col-xs12" >
                <div class="layui-input-block">  <#--lay-submit="" layui提交校验-->
                    <button <#--type="button"--> class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateModule">确认</button>
                    <button <#--type="button"--> class="layui-btn layui-btn-lg layui-btn-normal" id="qwe">取消</button>
                </div>
            </div>
        </form>

    <script type="text/javascript" src="${ctx}/js/module/add_update.js"></script>
    </body>
</html>