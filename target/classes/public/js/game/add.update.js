layui.use(['form', 'layer', 'formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var formSelects = layui.formSelects;
    /**
     * 表单Submit监听
     */
    form.on('submit(addOrUpdateGame)', function (data) {
        var index = top.layer.msg("数据提交中,请稍后...",{
            icon:16,
            time:false,
            shade:0.8
        });
        // 得到所有的表单元素的值
        var formData = data.field;
        var url = ctx + "/game/add";
        if ($("[name='id']").val()) {
            url = ctx + "/game/update";
        }
        $.post(url, formData, function (result) {
            if (result.code == 200) {
                top.layer.msg("操作成功！",{icon:6});
                top.layer.close(index);
                layer.closeAll("iframe");
                parent.location.reload();
            } else {
                layer.msg(result.msg, {icon:5});
            }
        });
        // 阻止表单提交
        return false;
    });
    /**
     * 关闭弹出层
     */
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
    /**
     * 设置下拉框
     */
    $.ajax({
        type:"get",
        url:ctx + "/game/queryAllStater",
        data:{},
        success:function (data) {
            if (data != null){
                var stater = $("#staterId").val();
                for (var i = 0;i < data.length; i++){
                    var opt = "";
                    if (stater == data[i].stater){
                        opt = "<option value='"+data[i].stater+"' selected>"+formatStater(data[i].stater)+"</option>";
                    }else {
                        opt = "<option value='"+data[i].stater+"'>"+formatStater(data[i].stater)+"</option>";
                    }
                    $("#stater").append(opt);
                }
            }
            layui.form.render("select");
        }
    });

    function formatStater(stater) {
        if (stater == 0) {
            return "<div style='color:yellow'>未接单</div>";
        } else if (stater == 1) {
            return "<div style='color:orange'>进行中</div>";
        } else if (stater == 2) {
            return "<div style='color:green'>已完成</div>";
        } else if (stater == 3) {
            return "<div style='color:red'>失败</div>";
        }
    }

});