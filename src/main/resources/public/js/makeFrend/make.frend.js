

layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //用户列表展示
    var arr = document.cookie.split("; ");
    item = arr[2];
    var  arr1 = item.split("=");
    var joinman = arr1[1]
    var tableIns=  table.render({
        id : "saleChanceList",
        elem: '#frendList',
        url : ctx +'/frend/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",

        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'活动编号',fixed:"true"},
            {field: 'createMan', title: '发起人',align:"center"},
            {field: 'maxNumbers', title: '最大人数',  align:'center'},
            {field: 'place', title: '活动地点', align:'center'},
            {field: 'type', title: '活动类型', align:'center'},
            {field: 'createDate', title: '开始时间',  align:'center'},
            {field: 'isComplate', title: '是否结束', align:'center'},
            {field: 'complateDate', title: '截至时间', align:'center'},
            {field: 'activityName', title: '活动名称', align:'center'},
            {field: 'createManPhone', title: '创建人电话', align:'center'},
    {title: '操作', templet:'#JoinFrendBar',fixed:"right",align:"center", minWidth:280}
           /* ,
            {field: 'state', title: '分配状态', align:'center',templet:function(d){
                    return formatterState(d.state);
                }},
            {field: 'devResult', title: '开发状态', align:'center',templet:function (d) {
                    return formatterDevResult(d.devResult);
                }},
            {title: '操作', templet:'#saleChanceListBar',fixed:"right",align:"center", minWidth:150}*/
        ]]
    });

    function formatterState(state){
        if(state==0){
            return "<div style='color:red; '>未分配</div>";
        }else if(state==1){
            return "<div style='color: green'>已分配</div>";
        }else{
            return "<div style='color: red'>未知</div>";
        }
    }

  /*  function formatterDevResult(value){
        /!**
         * 0-未开发
         * 1-开发中
         * 2-开发成功
         * 3-开发失败
         *!/
        if(value==0){
            return "<div style='color: red'>未开发</div>";
        }else if(value==1){
            return "<div style='color: #00FF00;'>开发中</div>";
        }else if(value==2){
            return "<div style='color: #00B83F'>开发成功</div>";
        }else if(value==3){
            return "<div style='color: red'>开发失败</div>";
        }else {
            return "<div style='color: #af0000'>未知</div>"
        }
    }*/

    // 多条件搜索
    $(".search_btn").click(function () {

        tableIns.reload({
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                createMan: $("input[name='createMan']").val(),  //创建人
                activityName: $("input[name='activityName']").val(),  //活动名称
                maxNumbers: $("input[name='maxNumbers']").val(),
            }
        });
    });



    //头工具栏事件
    table.on('toolbar(frend)',function (data) {
        console.log(data);
        if (data.event == "add"){
            openwindow();
        }else if (data.event == "del"){
            deleteSaleChance(data);
        }
    });

    function deleteSaleChance(data) {
        //获取数据选中的行数据
        var checkStatus = table.checkStatus("saleChanceList");

        var saleChance = checkStatus.data;

        console.log(saleChance)

        //判断用户是否选择额记录 （进行行的数量>0)
        if ( saleChance.length < 1){
            layer.msg("请选择要删除的记录" ,{icon:5});
            return;
        }

        // 如果记录不为空 则 询问 用户是否要删除
        layer.confirm('您确定要删除所选中的记录吗',{icon:3,title:'营销机会管理'},function (index) {
        //    关闭 确认框
            layer.close(index);
            //传递数组参数
            var ids = "ids=";
        //    循环选中记录行的数据
            for (var i = 0; i<saleChance.length; i++){
                if (i<saleChance.length -1){
                    ids = ids + saleChance[i].id+ "&ids=";
                }else {
                    ids = ids + saleChance[i].id;
                }

            }
        //   发送ajax 请求到 后端
            $.ajax({
                type:"post",
                url: ctx + "/sale_chance/delete",
                data:ids,
                success:function (result) {
                    if (result.code == 200){
                        layer.msg("删除成功",{icon:6});
                        tableIns.reload({
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }});
                    }else {
                        layer.msg(result.msg,{icon:5});
                    }
                }

            })


        })

    }


    //  发起活动
    function openwindow(){
        var title = "<h2>发起聚会</h2>"
        var url = ctx + "/frend/addFrendPage"

    /*    if (saleChanceId !=null && saleChanceId != '' ){
            title = "<h2>营销机会管理-修改记录</h2>"

            url += '?saleChanceId=' + saleChanceId;
        }*/

        layer.open({
            type: 2,
            title: title,
            shadeClose: true,
            area: ["600px", '90%'],
            content: url,
            maxmin:true,
            shade:0.8
        })
    }

    table.on('tool(frend)',function (data) {
        console.log(data);
        if (data.event == "unjoin"){
           /* var saleChanceId = data.data.id;
            openwindow(saleChanceId);*/

            layer.confirm('确定取消参加这个活动吗',{icon:3,title:"取消参加活动"},function (index) {
                layer.close(index);//关闭确认框

                $.ajax({
                    type:"get",
                    url: ctx + "/join/frendunjoin",
                    data:{
                        id:data.data.id,

                    },
                    success:function (result) {
                        if (result.code == 200){
                            layer.msg(result.msg,{icon:6})
                            tableIns.reload();
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        }else {
                            layer.msg("加入失败",{icon:5})
                        }
                    }
                })
            })
        }else if (data.event == "join"){

            var arr = document.cookie.split("; ");
            item = arr[3];
            var  arr1 = item.split("=");
            console.log(arr1[1]);
            console.log(arr)
            layer.confirm('确定要参加这个活动吗',{icon:3,title:"参加聚会"},function (index) {
                layer.close(index);//关闭确认框


                $.ajax({
                    type:"get",
                    url: ctx + "/join/frend",
                    data:{
                        id:data.data.id,
                        joinMan:arr1[1],
                        activityName:data.data.activityName,
                        createManPhone:data.data.createManPhone,
                        createMan:data.data.createMan
                    },
                    success:function (result) {
                        if (result.code === 200) {
                            console.log(result)
                            layer.msg(result.msg, {icon: 6})
                            tableIns.reload();
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        } else {
                            layer.msg(result.msg, {icon: 5})
                        }
                    }
                })
            })

        } else if (data.event == "del"){ //删除操作
                layer.confirm('确定要删除这条记录吗',{icon:3, title:"营销机会管理"}, function (index) {
                    layer.close(index);//关闭确认框

                    $.ajax({
                        type:'post',
                        url: ctx + "/sale_chance/delete",
                        data:   {
                            ids:data.data.id
                        },
                        success:function (result) {
                            if (result.code == 200){
                                layer.msg("删除成功",{icon:6});
                                tableIns.reload({
                                    page: {
                                        curr: 1 //重新从第 1 页开始
                                    }});
                            }else {
                                layer.msg("删除失败",{icon:5});
                            }
                        }
                    })

                })

        }

    });

});
