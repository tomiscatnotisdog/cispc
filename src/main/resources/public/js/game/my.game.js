layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 加载数据表格
     */
    var tableIns = table.render({
        id:'gameTable'
        // 容器元素的ID属性值
        ,elem: '#gameList'
        ,height: 'full-125'
        ,cellMinWidth:95
        ,url: ctx + '/game/queryMyGame'
        ,page: true
        ,limit:10
        ,limits:[10,20,30,40,50]
        ,toolbar:'#toolbarDemo'
        ,cols: [[
            {type:'checkbox', fixed:'center'}
            ,{field: 'id', title: '编号',  sort: true, fixed: 'left'}
            ,{field: 'gameName', title: '游戏名称', align:'center'}
            ,{field: 'gamePrice', title: '价格', align:'center'}
            ,{field: 'gameProfile', title: '游戏说明', align:'center'}
            ,{field: 'bond', title: '保证金', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '修改时间', align:'center'}
            ,{field: 'stater', title: '状态', align:'center',templet: function (d) {
                    return formatStater(d.stater);}}
            ,{title:'操作',templet:'#gameListBar', fixed: 'right', align:'center', minWidth:150}
        ]]
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

    /**
     * 搜索按钮的点击事件
     */
    $(".search_btn").click(function () {
        /**
         * 表格重载
         *  多条件查询
         */
        tableIns.reload({
            where: {
                gameName: $("[name='gameName']").val()
                ,stater:$("[name='stater']").val()
            }
            ,page: {
                curr: 1
            }
        });
    });
    /**
     * 监听头部工具栏
     */
    table.on('toolbar(games)', function (data) {
        if (data.event == "add") {
            openAddOrUpdateUserDialog();
        } else if (data.event == "delete") {
            var checkStatus = table.checkStatus(data.config.id);
            deleteUsers(checkStatus.data);
        }
    });
    /**
     * 监听行工具栏
     */
    table.on('tool(games)', function (data) {
        if (data.event == "edit") {
            openAddOrUpdateUserDialog(data.data.id);
        } else if (data.event == "delete") {
            deleteUser(data.data.id);
        }
    });
    /**
     * 删除多条用户记录
     * @param gameData
     */
    function deleteUsers(gameData) {
        if (gameData.length == 0) {
            layer.msg("请选择要删除的记录！", {icon:5});
            return;
        }
        layer.confirm('您确定要删除选中的记录吗？',{icon:3, title:'游戏管理'}, function (index) {
            layer.close(index);
            var ids = "ids=";
            for(var i = 0; i < gameData.length; i++) {
                if(i < gameData.length -1) {
                    ids = ids + gameData[i].id + "&ids="
                } else {
                    ids = ids + gameData[i].id;
                }
            }
            $.ajax({
                type:"post",
                url:ctx + "/game/delete",
                data:ids,
                success:function (result) {
                    if (result.code == 200) {
                        layer.msg("删除成功！",{icon:6});
                        tableIns.reload();
                    } else {
                        layer.msg(result.msg, {icon:5});
                    }
                }
            });
        });

    }
    /**
     * 删除单条用户记录
     * @param id
     */
    function deleteUser(id) {
        layer.confirm('确定要删除该记录吗？',{icon:3, title:"游戏管理"}, function (index) {
            layer.close(index);
            $.ajax({
                type:"post",
                url:ctx + "/game/delete",
                data:{
                    ids:id
                },
                success:function (result) {
                    if (result.code == 200) {
                        layer.msg("删除成功！",{icon:6});
                        tableIns.reload();
                    } else {
                        layer.msg(result.msg, {icon:5});
                    }
                }
            });
        });
    }
    /**
     * 打开添加/修改用户的对话框
     */
    function openAddOrUpdateUserDialog(id) {
        var title = "<h3>添加游戏</h3>";
        var url = ctx + "/game/toAddOrUpdateGame";
        if (id != null && id != '') {
            title = "<h3>修改游戏</h3>";
            url+= "?id=" +id;
        }
        layui.layer.open({
            type: 2,
            title: title,
            area: ['650px', '400px'],
            content: url,
            maxmin:true
        });
    }
});

