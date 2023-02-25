layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;



    form.on('submit(addOrUpdateFeedback)', function (data) {

        var index = layer.msg("数据正在提交,请稍后...", {
            icon: 16,
            time: false,
            shade: 0.8
        });
        var url = ctx + "/feedback/add";

        //id为空添加操作
        var feedBackId=$("[name='id']").val();

        if (feedBackId!=null && feedBackId!=''){
            url = ctx + "/feedback/update";
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

    /*关闭谈出层*/

    $("#closeBtn").click(function () {
        //得到iframe层索引
        var index = parent.layer.getFrameIndex(window.name);
        //在执行关闭
        parent.layer.close(index);
    });

});