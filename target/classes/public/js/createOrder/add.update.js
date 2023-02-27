layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    // layui.use('laydate', function() {
    //     var laydate = layui.laydate;
    //
    //     //执行一个laydate实例
    //
    //     laydate.render({
    //         elem: '#createDate',
    //         type: 'datetime'//指定元素
    //     });
    // });
    /**
     * 表单Submit监听
     */

    form.on('submit(addOrUpdateOrder)',function (data) {
        //提交数据时的加载层
        var index = top.layer.msg("数据提交中，请稍后。。。",{
            icon:16,//图标
            time:false,//不关闭
            shade:0.8//设置遮罩的透明度
        });
        //得到所有的表单元素的值
        var formData = data.field;
        console.log(formData);

        //请求的地址
        var url = ctx + "/create_order/create";
        //判断计划项id是否为空

        var createOrderId = $("[name='id']").val();
        // 判断ID是否为空
        if (createOrderId != null && createOrderId != '') {
            // 更新操作
            url = ctx + "/create_order/update";
        }
        $.post(url,formData,function (result) {
            //判断操作是否执行成功 200=成功
            if(result.code == 200){
                //成功
                //提示成功
                top.layer.msg("操作成功！",{icon: 6});
                //关闭加载层
                top.layer.close(index);
                layer.closeAll("iframe");
                //刷新父窗口，重新加载数据
                parent.location.reload();
            }else {
                //失败
                layer.msg(result.msg,{icon:5});
            }

        });
        //阻止表单提交
        return false;
    });

    /**
     * 关闭弹出层
     */
    $("#closeBtn").click(function (){
        //当你在iframe页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name);//先得到当前iframe层的索引
        parent.layer.close(index);//再执行关闭
    });



});