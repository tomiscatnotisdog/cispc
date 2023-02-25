layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        layuimini = layui.layuimini;


    //通过class选择器为搜索按钮绑定点击事件
    $(".layui-btn-primary").click(function () {
        //这里以搜索为例 通过表格对象绑定重新加载
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设  如:  后台需要的参数名:前台传递的数据
                identityName: $("[name='identityName']").val(),  //角色名称(通过name选择器获取)
            },
            page: {
                curr: 1 //重新从第 1 页开始
            }
        })
    });


    /*数据表格*/
    var tableIns = table.render({
        elem: '#currentIdentityModuleId',
        id: "identityListIdentityModule",
        url: ctx + '/identityModule/list',
        cellMinWidth: 95,
        limits: [10, 15, 20, 25, 50, 100],
        limit: 10,
        page: true,
        height: "full-125",
        toolbar: "#toolbarDemo",
        cols: [[
            {type: "checkbox", /*width: 50,*/ fixed: "left"},
            {field: 'id', /*width: 40,*/ title: 'ID', align: "center"},
            {field: 'identityName', /*width: 90,*/ title: '角色名称', align: "center"},
            {field: 'moduleName', /*width: 90,*/ title: '资源名称', align: "center"},
            {field: 'createDate', /*width: 165,*/ title: '创建时间', sort: true, align: "center"},
            {field: 'updateDate', /*width: 165,*/ title: '修改时间', sort: true, align: "center"},
            //行工具栏
            {title: '操作', width: 160, templet: '#currentIdentityModuleBar', fixed: "right", align: "center"}
        ]]
    });


    /**
     * 监听头部根据栏
     */  //toolbar(表格的lay-filter属性值)
    table.on('toolbar(currentIdentityModuleFilter)', function (data) {
        /**
         * 用户添加与修改的ifream弹出框
         */
        layui.layer.open({
            type: 2,
            //标题
            title: "<h3>角色授权</h3>",
            //宽高
            area: ['425px', '500px'],
            //开启最大最小化
            maxmin: true,
            //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            content: [ctx + "/identityModule/toWarrant", 'no']
        });

    });


    /**
     * 监听行工具栏(权限移除)
     */
    table.on('tool(currentIdentityModuleFilter)', function (data) {
        if (data.event === "delete") {
            //询问用户是否是真的要删除
            layer.confirm("你确定要移除名称授权吗?", {icon: 3, title: "系统提示"}, function (index) {
                //点击确认后进入此方法,进入后通过索引将询问框关闭
                layer.close(index);
                //$.get(提交的url地址,提交的数据,成功的回调函数(后端传回的结果));
                //发送ajax(post)请求,删除此记录
                $.post(ctx + "/identityModule/delete", {id: data.data.id}, function (result) {
                    if (result.code === 200) {
                        //表示删除成功
                        layer.msg("权限移除成功!", {icon: 6});
                        //刷新表格数据
                        tableIns.reload();
                    } else {
                        //表示删除失败
                        layer.msg(result.msg, {icon: 5});
                    }
                })
            });
        }
    });
});