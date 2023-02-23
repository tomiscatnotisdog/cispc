<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:90%;">
            <#--用户ID-->
            <input name="id" type="hidden" value=""/>
            <#--用户名称-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">用户名称</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="userName" id="userName"  value="" placeholder="请输入用户名称">
                </div>
            </div>

            <#--用户性别-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">用户性别</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input sex"
                           lay-verify="required" name="sex" id="sex"  value="" placeholder="请输入用户性别">
                </div>
            </div>
            <#--城市-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">居住城市</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input city"
                           lay-verify="required" name="city" id="city" value="" placeholder="请输入所在的城市">
                </div>
            </div>
            <#--用户身份-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">用户身份</label>
                <div class="layui-input-block">
                    <select name="identity" id="identity"  >
                        <option disabled value="" selected>请选择用户身份</option>
                    </select>
                </div>
            </div>
            <#--联系方式-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">联系方式</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input phone"
                           lay-verify="phone" name="phone" id="phone" value="" placeholder="请输入联系方式">
                </div>
            </div>
            <#--用户邮箱-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">用户邮箱</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input email"
                           lay-verify="email" name="email" id="email"  value="" placeholder="请输入用户邮箱">
                </div>
            </div>
            <#--个性签名-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">个性签名</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input sign"
                           lay-verify="" name="sign" id="sign" value=""  placeholder="请输入个性签名">
                </div>
            </div>
            <br/>
            <div class="layui-form-item layui-row layui-col-xs12" >
                <div class="layui-input-block">  <#--lay-submit="" layui提交校验-->
                    <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addTable">确认</button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
                </div>
            </div>
        </form>

    <script type="text/javascript" src="${ctx}/js/page/table_add.js"></script>
    </body>
</html>