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
                moduleName: $("[name='moduleName']").val(),  //资源名称(通过name选择器获取)
                originator: $("[name='originator']").val(), //资源作者(通过name选择器获取)
            },
            page: {
                curr: 1 //重新从第 1 页开始
            }
        })
    });


    /*数据表格*/
    var tableIns = table.render({
        elem: '#currentModuleId',
        id: "tableListModule",
        url: ctx + '/module/list',
        cellMinWidth: 95,
        limits: [10, 15, 20, 25, 50, 100],
        limit: 10,
        page: true,
        height: "full-125",
        toolbar: "#toolbarDemo",
        cols: [[
            {type: "checkbox",  fixed: "left"},
            {field: 'id',  title: 'ID', align: "center"},
            {field: 'code',  title: '资源码', align: "center"},
            {field: 'moduleName',  title: '资源名称', align: "center"},
            {field: 'originator',  title: '资源作者', sort: true, align: "center"},
            {field: 'parentId',  title: '所属资源', align: "center"},
            {field: 'url',  title: '资源路径', align: "center"},
            {field: 'createDate',  title: '创建时间', sort: true, align: "center"},
            {field: 'updateDate',  title: '修改时间', sort: true, align: "center"},
            {title: '操作',  templet: '#currentTableBar', fixed: "right", align: "center"}
        ]]
    });




    /**
     * 监听头部根据栏
     */  //toolbar(表格的lay-filter属性值)
    table.on('toolbar(currentModuleFilter)',function (data) {
        if (data.event==="add"){
            //调用更新或添加营销机会的展示页面,传入营销机会id
            toAddOrUpdate();
        }else if (data.event==="del"){
            //获取被选中的数据的信息
            var checkStatus=table.checkStatus(data.config.id);
            //调用移除方法
            deleteTable(checkStatus.data);
        }
    });




    /**
     * 头部工具栏的移除函数
     * @param data
     */
    function deleteTable(data){
        //data中存放的是table的数组
        var moduleIds="moduleIds=";
        //循环拿去选择的数据ID
        for (var i=0;i<data.length;i++){
            if (i<data.length-1){
                moduleIds+=data[i].id+"&moduleIds=";
            }else {
                moduleIds+=data[i].id;
            }
        }
        //发送ajax到后台,执行移除
        $.post(ctx+"/module/delete?"+moduleIds,{},function (resultInfo) {
            if (resultInfo.code===200){
                //表示移除成功
                layer.msg("资源移除成功!",{icon:6});
                //刷新表格数据
                tableIns.reload();
            }else {
                //表示移除失败
                layer.msg(resultInfo.msg,{icon:5});
            }
        })

    }


    /**
     * 监听行工具栏
     */
    table.on('tool(currentModuleFilter)',function (data) {
        if (data.event==="edit"){
            //调用更新或添加营销机会的展示页面,传入营销机会id
            toAddOrUpdate(data.data.id);
        }else if (data.event==="delete"){
            //询问用户是否是真的要移除
            layer.confirm("你确定要本资源移除吗?",{icon:3,title:"表格展示管理"},function (index) {
                //点击确认后进入此方法,进入后通过索引将询问框关闭
                layer.close(index);
                //$.get(提交的url地址,提交的数据,成功的回调函数(后端传回的结果));
                //发送ajax(post)请求,移除此资源
                $.post(ctx+"/module/delete",{moduleIds:data.data.id},function (result) {
                    if (result.code===200){
                        //表示移除成功
                        layer.msg("资源移除成功!",{icon:6});
                        //刷新表格数据
                        tableIns.reload();
                    }else {
                        //表示移除失败
                        layer.msg(result.msg,{icon:5});
                    }
                })
            });
        }
    });


    /**
     * 用户添加与修改的ifream弹出框
     */
    function toAddOrUpdate(moduleId) {
        var title="<h3>添加资源信息</h3>";
        var url=[ctx+"/module/toAddOrUpdate",'yes'];
        if (moduleId!=null){
            title="<h3>更新资源信息</h3>";
            url=[ctx+"/module/toAddOrUpdate?moduleId="+moduleId,'yes']
        }
        layui.layer.open({
            type: 2,
            //标题
            title:title,
            //宽高
            area:['425px','400px'],
            //开启最大最小化
            maxmin:true,
            //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            content:url
        });
    }

});