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
                userName: $("[name='userName']").val(),  //用户名称(通过name选择器获取)
                sex: $("[name='sex']").val(), //用户性别(通过name选择器获取)
                identity:$("[name='identity']").val() //用户身份(通过name选择器获取)
            },
            page: {
                curr: 1 //重新从第 1 页开始
            }
        })
    });



    /**
     * 监听头部根据栏
     */  //toolbar(表格的lay-filter属性值)
    table.on('toolbar(currentTableFilter)',function (data) {
        if (data.event==="add"){
            //调用更新或添加营销机会的展示页面,传入营销机会id
            toAdd();
        }else if (data.event==="del"){
            //获取被选中的数据的信息
            var checkStatus=table.checkStatus(data.config.id);
            //调用删除方法
            deleteTable(checkStatus.data);
        }
    });

    /**
     * 用户的添加的ifream弹出框
     */
    function toAdd() {
        layui.layer.open({
            type: 2,
            //标题
            title:"<h3>添加用户记录</h3>",
            //宽高
            area:['425px','500px'],
            //开启最大最小化
            maxmin:true,
            //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            content:[ctx+"/table/toAdd",'yes']
        });
    }


    /**
     * 头部工具栏的删除函数
     * @param data
     */
    function deleteTable(data){
        //data中存放的是table的数组
        var tableIds="tableIds=";
        //循环拿去选择的数据ID
        for (var i=0;i<data.length;i++){
            if (i<data.length-1){
                tableIds+=data[i].id+"&tableIds=";
            }else {
                tableIds+=data[i].id;
            }
        }
        //发送ajax到后台,执行删除
        $.post(ctx+"/table/delete?"+tableIds,{},function (resultInfo) {
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
    table.on('tool(currentTableFilter)',function (data) {
        if (data.event==="edit"){
            //调用更新或添加营销机会的展示页面,传入营销机会id
            toUpdate(data.data.id);
        }else if (data.event==="delete"){
            //询问用户是否是真的要删除
            layer.confirm("你确定要本条记录删除吗?",{icon:3,title:"表格展示管理"},function (index) {
                //点击确认后进入此方法,进入后通过索引将询问框关闭
                layer.close(index);
                //$.get(提交的url地址,提交的数据,成功的回调函数(后端传回的结果));
                //发送ajax(post)请求,删除此记录
                $.post(ctx+"/table/delete",{tableIds:data.data.id},function (result) {
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
     * 用户的修改的ifream弹出框
     */
    function toUpdate(tableId) {
        layui.layer.open({
            type: 2,
            //标题
            title:"<h3>修改用户记录</h3>",
            //宽高
            area:['425px','650px'],
            //开启最大最小化
            maxmin:true,
            //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            content:[ctx+"/table/toUpdate?tableId="+tableId,'no']
        });
    }



    /*数据表格*/
    var tableIns = table.render({
        elem: '#currentTableId',
        id: "tableListTable",
        url: ctx + '/table/list',
        cellMinWidth: 95,
        limits: [10, 15, 20, 25, 50, 100],
        limit: 10,
        page: true,
        height: "full-125",
        toolbar: "#toolbarDemo",
        cols: [[
            {type: "checkbox", width: 50, fixed: "left"},
            {field: 'id', width: 40, title: 'ID', align: "center"},
            {field: 'userName', width: 90, title: '用户名称', align: "center"},
            {field: 'sex', width: 82, title: '性别', sort: true, align: "center"},
            {field: 'city', width: 60, title: '城市', align: "center"},
            {field: 'identity', width: 85, title: '身份', align: "center"},
            {field: 'phone', width: 120, title: '联系方式', align: "center"},
            {field: 'sign', title: '签名', minWidth: 150, align: "center"},
            {field: 'createDate', width: 165, title: '创建时间', sort: true, align: "center"},
            {field: 'updateDate', width: 165, title: '修改时间', sort: true, align: "center"},
            {field: 'experience', width: 82, title: '积分', sort: true, align: "center"},
            {field: 'score', width: 82, title: '评分', sort: true, align: "center"},
            {field: 'wealth', width: 90, title: '财富', sort: true, align: "center"},
            {field: 'email', width: 215, title: '邮箱', align: "left"},
            {title: '操作', width: 115, templet: '#currentTableBar', fixed: "right", align: "center"}
        ]]
    });
});