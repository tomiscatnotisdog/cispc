layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //用户列表展示

    var arr = document.cookie.split("; ");
    item = arr[3];
    var  arr1 = item.split("=");
    var createMan = arr1[1];
    var  tableIns = table.render({
        elem: '#saleChanceList',
        url : ctx+'/join/createlist?createMan='+createMan,
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "ListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'活动编号',fixed:"true"},
            {field: 'activityName', title: '活动名称',align:"center"},
            {field: 'place', title: '活动地点',  align:'center'},
            {field: 'type', title: '活动类型', align:'center'},
            {field: 'createMan', title: '创建人', align:'center'},
            {field: 'createDate', title: '创建时间',  align:'center'},
            {field: 'complateDate', title: '完成时间', align:'center'},
            {field: 'isComplate', title: '是否完成', align:'center'},
           /* {field: 'createMan', title: '创建人', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'devResult', title: '开发状态', align:'center',templet:function (d) {
                    return formatterDevResult(d.devResult);
                }},*/
            {title: '操作',fixed:"right",align:"center", minWidth:150,templet:"#op"}
        ]]
    });

    function formatterDevResult(value){
        /**
         * 0-未开发
         * 1-开发中
         * 2-开发成功
         * 3-开发失败
         */
        if(value==0){
            return "<div style='color: yellow'>未开发</div>";
        }else if(value==1){
            return "<div style='color: #00FF00;'>开发中</div>";
        }else if(value==2){
            return "<div style='color: #00B83F'>开发成功</div>";
        }else if(value==3){
            return "<div style='color: red'>开发失败</div>";
        }else {
            return "<div style='color: #af0000'>未知</div>"
        }
    }


    // 多条件搜索
    $(".search_btn").on("click",function(){
        table.reload("ListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                activityName: $("input[name='activityName']").val(),  //客户名
                type: $("input[name='type']").val(), //创建人
               /* devResult:$("#devResult").val()  //开发状态*/
            }
        });
    });






    /**
     * 行监听
     */
    table.on("tool(myfrend)", function(obj){
        var layEvent = obj.event;
   /*     if(layEvent === "dev") {
            openCusDevPlanDialog("计划项数据维护",obj.data.id);
        }else if(layEvent === "info") {
            openCusDevPlanDialog("计划项数据详情",obj.data.id);
        }*/
        if (layEvent === "disable"){
        //    取消当前我发起的活动

             openmyfrend(obj.data.id);
        }else if (layEvent ==="update"){
                openupdatefend(obj.data)
        }else if (layEvent === "check"){
                openupdatemycheck(obj.data);
        }

    });
    function openupdatemycheck(data) {
        var title = "<h2>查看活动详情</h2>"
        let id = data.id;
        console.log(id)
        var url = ctx + "/frend/checkinfo?id="+id;

        layer.open({
            type: 2,
            title: title,
            shadeClose: true,
            area: ["600px", '90%'],
            content: url,
            maxmin:true,
            shade:0.8
        });
    }
    function openupdatefend(data) {
        var title = "<h2>修改活动</h2>"
        let id = data.id;
        console.log(id)
        var url = ctx + "/frend/updatefrend?id="+id;

        layer.open({
            type: 2,
            title: title,
            shadeClose: true,
            area: ["600px", '90%'],
            content: url,
            maxmin:true,
            shade:0.8
        });
    }

    function openmyfrend(id) {
        // 如果记录不为空 则 询问 用户是否要删除
        layer.confirm('您确定要取消当前活动吗',{icon:3,title:'活动取消'},function (index) {
            //    关闭 确认框
            layer.close(index);
            //传递数组参数
            $.ajax({
                type:"post",
                url: ctx + "/frend/delete?id="+id,
                data:'',
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
    // 打开开发计划对话框
    function openCusDevPlanDialog(title,id){
        layui.layer.open({
            title : title,
            type : 2,
            area:["750px","550px"],
            maxmin:true,
            content : ctx+"/cus_dev_plan/toCusDevPlanDataPage?id="+id
        });
    }




});
