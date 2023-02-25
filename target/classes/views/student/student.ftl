<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="phone"
                           class="layui-input
					searchVal" placeholder="手机号码"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="calzzName" class="layui-input
					searchVal" placeholder="班级"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="userName" class="layui-input
					searchVal" placeholder="老师名字"/>
                </div>
                <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>

    <table id="userList" class="layui-table" lay-filter="users"></table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加班级
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除班级
            </a>
        </div>
    </script>
    <!--操作-->
    <script id="userListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
</form>
<script type="text/javascript">
    layui.use(['table', 'layer', "form"], function () {
        var layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery,
            table = layui.table;
        //用户列表展示
        var tableIns = table.render({
            elem: '#userList',
            url: ctx + '/student/list',
            cellMinWidth: 95,
            page: true,
            height: "full-125",
            limits: [10, 15, 20, 25],
            limit: 10,
            toolbar: "#toolbarDemo",
            id: "userListTable",
            cols: [[
                {type: "checkbox", fixed: "left", width: 50},
                {field: "id", title: '编号', fixed: "true", width: 80},
                {field: 'son', title: '班级编号', minWidth: 50, align: "center"},
                {field: 'clazzName', title: '班级名称', minWidth: 100, align: 'center'},
                {field: 'addresss', title: '班级人数', align: 'center'},
                {field: 'introducatio', title: '班级介绍', align: 'center', minWidth: 150},
                {field: 'userName', title: '老师名称', minWidth: 50, align: "center"},
                {field: 'genderr', title: '性别', minWidth: 50, align: "center"},
                {field: 'email', title: '邮箱', minWidth: 100, align: 'center'},
                {field: 'phone', title: '电话', align: 'center', minWidth: 150},
                {title: '操作', minWidth: 150, templet: '#userListBar', fixed: "right", align: "center"}
            ]]
        });


        // 多条件搜索
        $(".search_btn").on("click", function () {
            table.reload("userListTable", {
                page: {
                    curr: 1
                },
                where: {
                    userName: $("input[name='userName']").val(),
                    phone: $("input[name='phone']").val(),
                    calzzName: $("input[name='calzzName']").val()
                }
            })
        });


        // 头工具栏事件
        table.on('toolbar(users)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id)
            switch (obj.event) {
                case "add": //添加用户
                    openAddOrUpdateUserDialog();
                    break;
                case "del": //删除多个用户记录
                    deleStudent(checkStatus.data);
                    break;

            }
        });


        //工具条事件
        table.on('tool(users)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if (layEvent === 'edit') { //查看

                openAddOrUpdateUserDialog(data.id);
            } else if (layEvent === 'del') { //删除
                layer.confirm('真的删除行么', function (index) {

                    layer.close(index);
                    //向服务端发送删除指令
                    $.ajax({
                        type: "post",
                        url: ctx + "/student/delete",
                        data: {"id": data.id}, // 传递的参数是数组 ids=1&ids=2&ids=3
                        success: function (result) {
                            // 判断删除结果
                            if (result.code == 200) {
                                // 提示成功
                                layer.msg("删除成功！", {icon: 6});
                                // 刷新表格
                                tableIns.reload();
                            } else {
                                // 提示失败
                                layer.msg(result.msg, {icon: 5});
                            }
                        }
                    });
                });
            }
        });


        /**
         * 打开添加/修改用户的对话框
         */
        function openAddOrUpdateUserDialog(id) {
            var title = "<h3>班级管理 - 添加班级</h3>";
            var url = ctx + "/student/addOrUpdateDialog";

            // 判断id是否为空；如果为空，则为添加操作，否则是修改操作
            if (id != null && id != '') {
                title = "<h3>班级管理 - 更新班级</h3>";
                url += "?id=" + id; // 传递主键，查询数据使用
            }

            // iframe层
            layui.layer.open({
                // 类型
                type: 2,
                // 标题
                title: title,
                // 宽高
                area: ['600px', '600px'],
                // url地址
                content: url,
                // 可以最大化与最小化
                maxmin: true
            });
        }


        /**
         * 删除多条用户记录 头部工具栏
         * @param userData
         */
        function deleStudent(datas) {
            // 判断是否选择了要删除的记录
            if (datas.length == 0) {
                layer.msg("请选择要删除的班级", {icon: 5});
                return;
            }

            //删除
            layer.confirm("你确定删除吗", {
                btn: ["确定", "取消"]

            }, function (index) {
                //关闭
                layer.close(index)
                //收集数据
                var id = [];
                for (var x in datas) {
                    id.push(datas[x].id);
                }
                // 发送ajax请求，执行删除用户
                $.ajax({
                    type: "post",
                    url: ctx + "/student/delete",
                    data: {"id": id.toString()}, // 传递的参数是数组 ids=1&ids=2&ids=3
                    success: function (result) {
                        // 判断删除结果
                        if (result.code == 200) {
                            // 提示成功
                            layer.msg("删除成功！", {icon: 6});
                            // 刷新表格
                            tableIns.reload();
                        } else {
                            // 提示失败
                            layer.msg(result.msg, {icon: 5});
                        }
                    }
                });
            });
        };
    });
</script>

</body>
</html>