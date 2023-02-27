layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 加载数据表格
     */
    var tableIns = table.render({
        id:'allOrderTable'
        // 容器元素的ID属性值
        ,elem: '#allOrderList'
        // 容器的高度 full-差值
        ,height: 'full-125'
        // 单元格最小的宽度
        ,cellMinWidth:95
        // 访问数据的URL（后台的数据接口） 设置flag参数，表示查询的事客户开发计划数据
        ,url: ctx + '/create_order/allOrderList?flag=1'
        // 开启分页
        ,page: true
        // 默认每页显示的数量
        ,limit:10
        // 每页页数的可选项
        ,limits:[10,20,30,40,50]
        // 开启头部工具栏
        ,toolbar:'#toolbarDemo'
        // 表头
        ,cols: [[
            // field：要求field属性值与返回的数据中对应的属性字段名一致
            // title：设置列的标题
            // sort：是否允许排序（默认：false）
            // fixed：固定列
            {type:'checkbox', fixed:'center'}
            ,{field: 'id', title: '编号',  sort: true, fixed: 'left'}
            ,{field: 'stUserNumber', title: '学工号', align:'center'}
            ,{field: 'createMan', title: '发起人', align:'center'}
            ,{field: 'createDate', title: '发起时间', align:'center'}
            ,{field: 'context', title: '服务内容', align:'center'}
            ,{field: 'createManPhone', title: '联系电话', align:'center'}
            ,{field: 'createManLocation', title: '目的地', align:'center'}
            ,{field: 'completeDate', title: '完成时间', align:'center'}
            ,{field: 'isComplete', title: '订单状态', align:'center', templet: function (d) {
                    // 调用函数，返回格式化的结果
                    return formatDevResult(d.isComplete);
                }}
            ,{title:'操作',templet:'#op', fixed: 'right', align:'center', minWidth:150}
        ]]
    });

    /**
     * 格式化开发状态
     *  0 = 未开发
     *  1 = 开发中
     *  2 = 开发成功
     *  3 = 开发失败
     *  其他 = 未知
     * @param isComplete
     */
    function formatDevResult(isComplete) {
        if (isComplete == 0) {
            return "<div style='color: red'>已撤销</div>";
        } else if (isComplete == 1) {
            return "<div style='color: orange'>已发起</div>";
        } else if (isComplete == 2) {
            return "<div style='color: yellow'>接单中</div>";
        } else if (isComplete == 3) {
            return "<div style='color: green'>完成</div>";
        } else {
            return "<div style='color: blue'>未知</div>";
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
            // 设置需要传递给后端的参数
            where: { //设定异步数据接口的额外参数，任意设
                // 通过文本框/下拉框的值，设置传递的参数
                createMan:$("[name='createMan']").val() // 发起人
                ,createManLocation:$("[name='createManLocation']").val()//目的地
                ,createManPhone:$("[name='createManPhone']").val()//手机号
                ,stUserNumber:$("[name='stUserNumber']").val()//学工号
                ,isComplete:$("#isComplete").val() // 订单状态
            }
            ,page: {
                curr: 1 // 重新从第 1 页开始
            }
        });

    });


    /**
     * 监听头部工具栏
     */
    table.on('toolbar(allOrder)',function (data){
        if(data.event =="add"){//发起跑腿订单
            //打开添加或修改跑腿订单的页面
            openCreateOrUpdateOrderDialog();
        }else if(data.event == "receive"){
            receiveOrder(data);
        }
    });
    function receiveOrder(data){
        //获取数据表格选中的行数据 table.checkStatus('数据表格的ID属性值');
        var checkStatus = table.checkStatus("allOrderTable");
        console.log("checkStatus"+checkStatus);
        //获取所有被选中的记录对应的数据
        var createOrderData = checkStatus.data;

        // 判断用户是否选择的记录 (选中行的数量大于0)
        if (createOrderData.length < 1) {
            layer.msg("请选择接收的订单！",{icon:5});
            return;
        }

        // 询问用户是否确认删除
        layer.confirm('您确定要接收选中的订单吗？',{icon:3, title:'接收该条订单'}, function (index) {
            // 关闭确认框
            layer.close(index);
            // 传递的参数是数组   ids=1&ids=2&ids=3
            var ids = "ids=";
            // 循环选中的行记录的数据
            for(var i = 0; i < createOrderData.length; i++) {
                if(i < createOrderData.length -1) {
                    ids = ids + createOrderData[i].id + "&ids="
                } else {
                    ids = ids + createOrderData[i].id;
                }
            }
            // console.log(ids);

            // 发送ajax请求，执行订单撤销操作
            $.ajax({
                type:"post",
                url:ctx + "/create_order/receiveOrder",
                data:ids, // 传递的参数是数组 ids=1&ids=2&ids=3
                success:function (result) {
                    // 判断删除结果
                    if (result.code == 200) {
                        // 提示成功
                        layer.msg("接单成功！",{icon:6});
                        // 刷新表格
                        tableIns.reload();
                    } else {
                        // 提示失败
                        layer.msg(result.msg, {icon:5});
                    }
                }
            });
        });
    }
    /**
     * 打开添加或修改跑腿订单的页面
     * @param title
     * @param id
     */
    function openCreateOrUpdateOrderDialog(title, id) {
        // iframe层
        layui.layer.open({
            // 类型
            type: 2,
            // 标题
            title: title,
            // 宽高
            area: ['750px', '550px'],
            // url地址
            content: ctx + "/create_order/createRunningOrder",
            // 可以最大化与最小化
            maxmin:true
        });
    }

    /**
     * 行工具栏监听
     */
    table.on('tool(allOrder)', function (data) {

        // 判断类型
        if (data.event == "receive") { // 修改操作
            // 弹出确认框，询问用户是否确认删除
            layer.confirm('确定要接收该订单吗？',{icon:3, title:"接收跑腿订单"}, function (index) {
                // 关闭确认框
                layer.close(index);
                var arr = document.cookie.split("; ");
                item = arr[3];
                var  arr1 = item.split("=");
                var create = arr1[1];

                    // 发送ajax请求，删除记录
                $.ajax({
                    type:"post",
                    url:ctx + "/create_order/receiveOrder",
                    data:{
                        ids:data.data.id,
                        // create:data.stUserNumber
                    },
                    success:function (result) {
                        // 判断删除结果
                        if (result.code == 200) {
                            // 提示成功
                            layer.msg("接单成功！",{icon:6});
                            // 刷新表格
                            tableIns.reload();
                        } else {
                            // 提示失败
                            layer.msg(result.msg, {icon:5});
                        }
                    }
                });
            });
        }
    });

});
