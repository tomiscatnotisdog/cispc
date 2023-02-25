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
        ,url: ctx + '/game/query'
        ,page: true
        ,limit:10
        ,limits:[10,20,30,40,50]
        ,toolbar:'#toolbarDemo'
        ,cols: [[
            {type:'checkbox', fixed:'center'}
            ,{field: 'id', title: '编号',  sort: true, fixed: 'left'}
            ,{field: 'gId', title: '用户id', align:'center'}
            ,{field: 'gameName', title: '游戏名称', align:'center'}
            ,{field: 'gamePrice', title: '价格', align:'center'}
            ,{field: 'gameProfile', title: '游戏说明', align:'center'}
            ,{field: 'bond', title: '保证金', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '修改时间', align:'center'}
            ,{field: 'stater', title: '状态', align:'center',templet: function (d) {
                    return formatStater(d.stater);}}
            ,{field: 'phone', title: '手机号', align:'center'}
            ,{field: 'trueName', title: '姓名', align:'center'}
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
                ,gamePrice: $("[name='gamePrice']").val()
                ,gameProfile:$("[name='gameProfile']").val()
            }
            ,page: {
                curr: 1
            }
        });
    });
});

