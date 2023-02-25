<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">

</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(student.id)!}"/>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input son"
                   lay-verify="son" name="son" id="son" value="${(student.son)!}" placeholder="请输入班级编号">
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input clazzName"
                   lay-verify="clazzName" name="clazzName" id="clazzName" value="${(student.clazzName)!}"
                   placeholder="请输入班级名称">
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级人数</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input addresss"
                   lay-verify="addresss" name="addresss" id="addresss" value="${(student.addresss)!}"
                   placeholder="请输入班级人数">
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级介绍</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input introducatio"
                   lay-verify="introducatio" name="introducatio" value="${(student.introducatio)!}"
                   id="name"
                   placeholder="请输入班级介绍">
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">老师名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="userName" name="userName" id="userName" value="${(student.userName)!}"
                   placeholder="请输入老师名称">
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input genderr"
                   lay-verify="genderr" name="genderr" id="genderr" value="${(student.genderr)!}" placeholder="请输入性别">
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input email"
                   lay-verify="email" name="email" value="${(student.email)!}" id="email" placeholder="请输入邮箱">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input phone"
                   lay-verify="phone" name="phone" id="phone" value="${(student.phone)!}" placeholder="请输入电话">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateUser">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript">
    layui.use(['form', 'layer', 'formSelects'], function () {
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery;
        var formSelects = layui.formSelects;


        /**
         * 添加按钮的监听事件
         */
        form.on('submit(addOrUpdateUser)', function (data) {

            var index = layer.msg("数据正在提交,请稍后...", {
                icon: 16,
                time: false,
                shade: 0.8
            });
            var url = ctx + "/student/save";

            // 判断用户ID是否为空，如果不为空则为更新操作
            if ($("[name='id']").val()) {
                // 更新操作
                var url = ctx + "/student/studentdelete";
            }
            //发送ajax请求
            $.post(url, data.field, function (res) {
                //判断是否成功
                if (res.code == 200) {
                    layer.msg("操作成功");

                    layer.close(index);
                    //关闭弹出层
                    layer.closeAll("iframe");
                    // 刷新父页面
                    parent.location.reload();
                } else {
                    layer.msg(res.msg,{icon:5});
                }
            });
            //组织表单提交
            return false;
        });
        

        //取消跳转
        $("#closeBtn").click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        })
    });

</script>
</body>
</html>