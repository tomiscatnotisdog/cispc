layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
    laydate.render({
            elem: '#createDate',
            type:'datetime'//指定元素
        });

    laydate.render({
        elem: '#complateDate',
        type:'datetime'//指定元素
    });
    });

   // 关闭当前 弹出层
   $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
   });




/*   $.ajax({
       type:"get",
       url: ctx + "/user/queryAllSales",
       data:{},
       success:function (data) {
           //获取隐藏域中的id


           if (data != null){

               var assignManId = $("#assignManId").val();

               for (var i = 0 ;i < data.length; i++){

                   var opt = "";

                   if (assignManId == data[i].id){
                       opt = "<option value='"+data[i].id+"' selected>"+data[i].uname+"</option>";
                   }else {
                       opt = "<option value='"+data[i].id+"'>"+data[i].uname+"</option>";
                   }

                   $("#assignMan").append(opt);
               }
           }
        layui.form.render("select");
       }
   });*/

   form.on('submit(addComment)',function (data) {
       var index = layer.msg("数据提交中 亲稍后",{
           icon:16,
           time:false,
           shade:0.8
       });

       //   发送ajax
       var url = ctx + "/frend/add";

   /*    var saleChanceId = $("[name='id']").val()
       if (saleChanceId != null && saleChanceId != ''){
           var url = ctx + "/sale_chance/update";
       }*/
       $.post(url,data.field,function (result) {
           if (result.code == 200){
               layer.msg("操作成功",{icon:6});
               layer.close(index);

               layer.closeAll("iframe");

               parent.location.reload();
           }else {
               layer.msg(result.msg,{icon:5});
           }
       });
        return false;
   });


});