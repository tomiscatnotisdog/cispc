layui.use(['form', 'layer', 'formSelects', 'laytpl', 'layedit'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        layedit = layui.layedit,
        laytpl = layui.laytpl;
    var formSelects = layui.formSelects;


    /**
     * 加载身份信息到下拉框
     */
    //$.get(提交的url地址,{配置项},成功的回调函数(后端传回的结果));
    $.get(ctx + "/identity/allIdentity", null, function (data) {
        if (data != null) {

            var opt = null;
            //遍历返回的数据
            for (var i = 0; i < data.length; i++) {
                opt = "<option value='" + data[i].nameIdentity + "'>" + data[i].nameIdentity + "</option>";
                //将下拉项添加到下拉框中
                $("#identity").append(opt);
            }
        }
        //重新渲染下拉框的值
        layui.form.render("select");
    });


    /**
     * 加载身份信息到复选框(后期动态渲染无法加载,页面写死)
     */
    // //$.get(提交的url地址,{配置项},成功的回调函数(后端传回的结果));
    // $.get(ctx + "/module/moduleIsOne", null, function (data) {
    //     if (data != null) {
    //         var opt = null;
    //         //遍历返回的数据
    //         for (var i = 0; i < data.length; i++) {
    //             opt = "<input type='checkbox' name='code' value='"+data[i].id+"' title=\"" + data[i].moduleName + "\">";
    //
    //             console.log(opt);
    //
    //             //将复选项放下复选框中
    //             $("#checkbox").append(opt);
    //         }
    //     }
    //     //重新渲染下拉框的值
    //     layui.form.render("select");
    // });


    /**
     * 监听提交事件(添加确认按钮)
     */
    form.on('submit(warrant)', function (data) {
        //console.log(data.field);//当前容器的全部表单字段，名值对形式：{name: value}-->saleChance对象
        var index = top.layer.msg('数据提交中，请稍候', {
            icon: 16,  //图标
            time: false,  //不关闭
            shade: 0.5 //设置遮罩的透明度
        });

        //获取要授权的身份
        var identityName=data.field.identityName;
        //获取要赋予的资源码
        var code='code=';
        var fu=document.getElementsByName("code");
        for (var i=0;i<fu.length;i++){
            if (fu[i].checked) {
                code+=fu[i].value + '&code=';
            }
        }
        code=code.substring(0,code.length-6);
        var url = ctx + "/identityModule/warrant?identityName="+identityName+"&"+code;  //提交的地址
        $.post(url, {}, function (resultInfo) {
            //判断从后台返回的结果
            if (resultInfo.code === 200) {
                //操作成功成功
                layer.msg(resultInfo.msg, {icon: 6});
                //关闭加载层
                layer.close(index);
                //关闭表单
                layer.closeAll("iframe");
                //刷新父窗口,重新加载数据展示
                parent.location.reload();
            } else {
                //更新失败
                layer.msg(resultInfo.msg, {icon: 5})
            }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });


    /**
     * 关闭弹出层(取消按钮)
     */
    $("#qwe").click(function () {
        //当你在iframe页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });
});