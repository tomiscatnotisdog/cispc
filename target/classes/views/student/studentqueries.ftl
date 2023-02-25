<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id"  value="${(stuxuehao.id)!}">

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"

                   lay-verify="required" name="studentname" id="studentname"  value="${(stuxuehao.studentname)!}"  placeholder="请输入姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">学号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="student" id="student" placeholder="请输入学号" value="${(stuxuehao.student)!}">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">系部</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="department"  lay-verify="required"
                   placeholder="请输入系部" value="${(stuxuehao.department)!}">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">专业</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="specialized" name="specialized" id="specialized" placeholder="请输入专业" value="${(stuxuehao.specialized)!}">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="clasnam"  id="clasnam" placeholder="请输入班级" value="${(stuxuehao.clasnam)!}">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">请假理由</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入机会描述信息" name="reason" class="layui-textarea">${(stuxuehao.reason)!}</textarea>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">审核人</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="approver" placeholder="请输入审核人" value="${(stuxuehao.approver)!}">
        </div>
    </div>




    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <select name="devresult"  id="devresult">
                <option value="" >状态</option>
                <option value="已批">已批</option>
                <option value="未批" >未批</option>
            </select>

        </div>
    </div>


    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateSaleChance">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" >
    layui.use(['form', 'layer', 'formSelects'], function () {
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery;
        var formSelects = layui.formSelects;


        /**
         * 添加按钮的监听事件
         */
        form.on('submit(addOrUpdateSaleChance)', function (data) {

            var index = layer.msg("数据正在提交,请稍后...", {
                icon: 16,
                time: false,
                shade: 0.8
            });
            var url = ctx + "/xuesheng/save";

            // 判断用户ID是否为空，如果不为空则为更新操作
            if ($("[name='id']").val()) {
                // 更新操作
                var url = ctx + "/xuesheng/studentdelete";
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