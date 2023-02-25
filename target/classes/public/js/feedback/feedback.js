layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    //数据表格
    var  tableIns = table.render({
        elem: '#feedbackList',
        url : ctx+'/feedback/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "feedbackTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'trueName', title: '真实姓名',align:"center"},
            {field: 'nickName', title: '昵称',  align:'center'},
            {field: 'phone', title: '联系电话', align:'center'},
            {field: 'updateDate', title: '更新时间', align:'center'},
            {field: 'content', title: '内容', align:'center'},
            {field: 'results', title: '分配状态', align:'center',templet: function (d) {
                    return formatFeedback(d.results);
                }},

            {title: '操作', templet:'#feedbackListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });
    //0未分配 1已分配其他位置
    function formatFeedback(results){
        if(results==0){
            return "<div style='color:dodgerblue '>正在处理</div>";
        }else if(results==1){
            return "<div style='color: green'>已处理</div>";
        }else{
            return "<div style='color: red'>未知</div>";
        }
    }

    // 多条件搜索
    $(".search_btn").on("click",function () {
        tableIns.reload({
            where:{
                trueName:$("input[name='trueName']").val(),// 客户名
                nickName:$("input[name='nickName']").val(),// 创建人
                results:$("#results").val()    //分配状态
            },
            page:{
                curr:1
            }
        });
    });


    // 添加删除的头部工具栏事件
    table.on('toolbar(feedBack)',function (obj) {
       //判断是添加还是删除事件
        if (obj.event=="add"){
            //添加操作
            opens();

        }else if (obj.event=="del"){
            //删除操作
            deleteTables(obj);

        }
    });


    //删除操作
    function deleteTables(data){
        //获取数据表格的行数据
        var checkStatus=table.checkStatus("feedbackTable")
        var feedbackObj=checkStatus.data;
         if(feedbackObj.length<1){
             layer.msg("请选择待删除记录!",{icon:5});
             return;
         }
        //询问是否要删除数据
         layer.confirm("确定删除选中的记录",{
             btn:['确定','取消']
         },function (index) {
             layer.close(index);
             // ids=10&ids=20&ids=30
             var ids="ids=";
             for(var i=0;i<feedbackObj.length;i++){
                 if(i<feedbackObj.length-1){
                     ids=ids+feedbackObj[i].id+"&ids=";
                 }else{
                     ids=ids+feedbackObj[i].id;
                 }
             }
             //发送ajax请求
             $.ajax({
                 type:"post",
                 url:ctx+"/feedback/delete",
                 data:ids,
                 dataType:"json",
                 success:function (ss) {
                     if(ss.code==200){
                         //成功
                         layer.msg("删除成功！！",{icon:6})
                         //刷新表格
                         tableIns.reload();
                     }else{
                         //提交失败
                         layer.msg(ss.msg,{icon:5});
                     }
                 }
             })



         })


     }


     //编辑删除按钮
      table.on('tool(feedBack)',function (obj) {

         var feedbackId =obj.data.id;

         if(obj.event === "edit"){

            opens(feedbackId);

         }else if(obj.event === "del"){

             layer.confirm("确认删除当前记录?",{icon: 3, title: "用户反馈"},function (index) {
                 //关闭确认框
                 layer.close(index);
                 $.post(ctx+"/feedback/delete",{ids:obj.data.id},function (result) {

                     if(result.code==200){
                         layer.msg("删除成功",{icon:6});

                         //刷新表格
                         tableIns.reload();
                     }else{
                         layer.msg(data.msg,{icon:5});
                     }
                 })
             })
         }

     });


     /**
      * 打开添加或更新对话框
      */
     function opens(feedbackId) {
         var title="添加";
         var url=ctx+"/feedback/toFeedbackPage";
         if(feedbackId !=null && feedbackId!=''){
             title="更新";
             url=url+"?feedbackId="+feedbackId;
         }
         layui.layer.open({
             title:title,
             type:2,
             area:["700px","500px"],
             maxmin:true,
             content:url
         })
     }








});
