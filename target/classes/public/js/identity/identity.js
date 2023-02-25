layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        layuimini = layui.layuimini;

    /*数据表格*/
    var tableIns = table.render({
        elem: '#currentIdentityId',
        id: "identityListTable",
        url: ctx + '/identity/list',
        cellMinWidth: 95,
        limits: [10, 15, 20, 25, 50, 100],
        limit: 10,
        page: true,
        height: "full-125",
        toolbar: "#toolbarDemo",
        cols: [[
            {type: "checkbox", /*width: 50,*/ fixed: "left"},
            {field: 'id', /*width: 40,*/ title: 'ID', align: "center"},
            {field: 'nameIdentity', /*width: 90,*/ title: '角色名称', align: "center"},
            {field: 'remarks', /*width: 90,*/ title: '角色备注', align: "center"},
            {field: 'createDate', /*width: 165,*/ title: '创建时间', sort: true, align: "center"},
            {field: 'updateDate', /*width: 165,*/ title: '修改时间', sort: true, align: "center"},
            //行工具栏
            {title: '操作', width: 160, templet: '#currentIdentityBar', fixed: "right", align: "center"}
        ]]
    });


    //通过class选择器为搜索按钮绑定点击事件
    $(".layui-btn-primary").click(function () {
        //这里以搜索为例 通过表格对象绑定重新加载
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设  如:  后台需要的参数名:前台传递的数据
                nameIdentity: $("[name='nameIdentity']").val(),  //角色名称(通过name选择器获取)
                remarks:$("[name='remarks']").val() //角色备注(通过name选择器获取)
            },
            page: {
                curr: 1 //重新从第 1 页开始
            }
        })
    });


    /**
     * 监听头部根据栏
     */  //toolbar(表格的lay-filter属性值)
    table.on('toolbar(currentIdentityFilter)',function (data) {
        if (data.event==="add"){
            //调用更新或添加营销机会的展示页面,传入营销机会id
            toAddOrUpdate();
        }else if (data.event==="del"){
            //获取被选中的数据的信息
            var checkStatus=table.checkStatus(data.config.id);
            //调用删除方法
            deleteTable(checkStatus.data);
        }
    });




    /**
     * 头部工具栏的删除函数
     * @param data
     */
    function deleteTable(data){
        //data中存放的是table的数组
        var identityIds="identityIds=";
        //循环拿去选择的数据ID
        for (var i=0;i<data.length;i++){
            if (i<data.length-1){
                identityIds+=data[i].id+"&identityIds=";
            }else {
                identityIds+=data[i].id;
            }
        }
        //发送ajax到后台,执行删除
        $.post(ctx+"/identity/delete?"+identityIds,{},function (resultInfo) {
            if (resultInfo.code===200){
                //表示删除成功
                layer.msg("记录删除成功!",{icon:6});
                //刷新表格数据
                tableIns.reload();
            }else {
                //表示删除失败
                layer.msg(resultInfo.msg,{icon:5});
            }
        })

    }


    /**
     * 监听行工具栏
     */
    table.on('tool(currentIdentityFilter)',function (data) {
        if (data.event==="edit"){
            //调用更新或添加营销机会的展示页面,传入营销机会id
            toAddOrUpdate(data.data.id);
        }else if (data.event==="delete"){
            //询问用户是否是真的要删除
            layer.confirm("你确定要本条记录删除吗?",{icon:3,title:"表格展示管理"},function (index) {
                //点击确认后进入此方法,进入后通过索引将询问框关闭
                layer.close(index);
                //$.get(提交的url地址,提交的数据,成功的回调函数(后端传回的结果));
                //发送ajax(post)请求,删除此记录
                $.post(ctx+"/identity/delete",{identityIds:data.data.id},function (result) {
                    if (result.code===200){
                        //表示删除成功
                        layer.msg("记录删除成功!",{icon:6});
                        //刷新表格数据
                        tableIns.reload();
                    }else {
                        //表示删除失败
                        layer.msg(result.msg,{icon:5});
                    }
                })
            });
        }
    });


    /**
     * 用户添加与修改的ifream弹出框
     */
    function toAddOrUpdate(identityId) {
        var title="<h3>添加角色记录</h3>";
        var url=[ctx+"/identity/toAddOrUpdate",'no'];
        if (identityId!=null){
            title="<h3>修改角色记录</h3>";
            url=[ctx+"/identity/toAddOrUpdate?identityId="+identityId,'no']
        }
        layui.layer.open({
            type: 2,
            //标题
            title:title,
            //宽高
            area:['425px','250px'],
            //开启最大最小化
            maxmin:true,
            //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            content:url
        });
    }
});