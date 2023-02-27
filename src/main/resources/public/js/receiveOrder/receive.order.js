layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 加载数据表格
     */
    var tableIns = table.render({
        id:'createOrderTable'
        // 容器元素的ID属性值
        ,elem: '#receiveOrderList'
        // 容器的高度 full-差值
        ,height: 'full-125'
        // 单元格最小的宽度
        ,cellMinWidth:95
        // 访问数据的URL（后台的数据接口） 设置flag参数，表示查询的事客户开发计划数据
        ,url: ctx + '/create_order/receiveOrderList?flag=1'
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
            ,{field: 'receiveMan', title: '接单人', align:'center'}
            ,{field: 'receiveDate', title: '接单时间', align:'center'}
            ,{field: 'context', title: '服务内容', align:'center'}
            ,{field: 'receiveManPhone', title: '接单人联系电话', align:'center'}
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
                receiveMan:$("[name='receiveMan']").val() // 接单人
                ,createManLocation:$("[name='createManLocation']").val()//目的地
                ,receiveManPhone:$("[name='receiveManPhone']").val()//接单人联系电话
                ,stUserNumber:$("[name='stUserNumber']").val()//学工号
                ,isComplete:$("#isComplete").val() // 订单状态
            }
            ,page: {
                curr: 1 // 重新从第 1 页开始
            }
        });

    });

    /**
     * 行工具栏监听
     */
    table.on('tool(receiveOrder)', function (data) {


        // 判断类型
        if (data.event == "update") { // 更新订单信息
            // 得到的创建订单的ID
            var createOrderId = data.data.id;
            // 打开更新订单详情页面
            opencreateOrderDialog(createOrderId)

        }else if (data.event == "delete") { // 删除操作
            // 弹出确认框，询问用户是否确认删除
            layer.confirm('确定要撤销该记录吗？',{icon:3, title:"撤销该条订单"}, function (index) {
                // 关闭确认框
                layer.close(index);

                // 发送ajax请求，删除记录
                $.ajax({
                    type:"post",
                    url:ctx + "/create_order/delete",
                    data:{
                        ids:data.data.id
                    },
                    success:function (result) {
                        // 判断删除结果
                        if (result.code == 200) {
                            // 提示成功
                            layer.msg("撤销成功！",{icon:6});
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
        function opencreateOrderDialog(createOrderId) {
            // 弹出层的标题
            var title = "<h3>跑腿订单管理 - 修改订单信息</h3>";
            var url = ctx + "/create_order/toUpdatePage";

            // 判断营销机会ID是否为空
            if (createOrderId != null && createOrderId != '') {
                // 更新操作
                // 请求地址传递跑腿订单的ID
                url += '?createOrderId=' + createOrderId;
            }

            // iframe层
            layui.layer.open({
                // 类型
                type: 2,
                // 标题
                title: title,
                // 宽高
                area: ['500px', '620px'],
                // url地址
                content: url,
                // 可以最大化与最小化
                maxmin:true
            });
        }
    });



    /**
     * 监听头部工具栏
     */
    table.on('toolbar(receiveOrder)',function (data){
        if(data.event =="add"){//发起跑腿订单
            //打开发起或更新跑腿订单的页面
            openCreateOrUpdateOrderDialog();
        }else if(data.event == "del"){//撤销订单
            deleteOrder(data);
        }
    });

    /**
     * 发起跑腿订单
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
     * 撤销订单
     */
    function deleteOrder(title,id) {
        //获取数据表格选中的行数据 table.checkStatus('数据表格的ID属性值');
        var checkStatus = table.checkStatus("createOrderTable");
        console.log("checkStatus"+checkStatus);
        //获取所有被选中的记录对应的数据
        var createOrderData = checkStatus.data;

        // 判断用户是否选择的记录 (选中行的数量大于0)
        if (createOrderData.length < 1) {
            layer.msg("请选择要撤销的记录！",{icon:5});
            return;
        }

        // 询问用户是否确认删除
        layer.confirm('您确定要撤销选中的记录吗？',{icon:3, title:'撤销该条订单'}, function (index) {
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
                url:ctx + "/create_order/delete",
                data:ids, // 传递的参数是数组 ids=1&ids=2&ids=3
                success:function (result) {
                    // 判断删除结果
                    if (result.code == 200) {
                        // 提示成功
                        layer.msg("撤销成功！",{icon:6});
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
