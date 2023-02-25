layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);
    // 菜单初始化
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();

    /**
     * .class类选择器
     *  退出登陆
     */
    $(".login-out").click(function () {
        //点击确定要调用的函数
        layer.confirm('你确定要退出登陆吗?', {icon: 3, title:'系统提示'}, function(index){
            //1.关闭提示框
            layer.close(index);
            //2.清除cookie
            $.removeCookie("userId",{domain:"localhost",path:"/cispc"});
            $.removeCookie("userNumber",{domain:"localhost",path:"/cispc"});
            $.removeCookie("password",{domain:"localhost",path:"/cispc"});
            $.removeCookie("trueName",{domain:"localhost",path:"/cispc"});
            //3.跳转到登陆页面
            window.parent.location.href=ctx+"/index"
        })

    });

    /**注销账户*/

    $(".login-cancellation").click(function(obj) {
        var arr = document.cookie.split("; ");
        item = arr[0];
        var  arr1 = item.split("=");

        var userNumber = arr1[1];



        // 弹出确认框询问用户是否确认删除
        layer.confirm('您确认删除账户吗？',{icon:3, title:"注销用户"}, function (index) {
            //1.关闭提示框
            layer.close(index);

            // 如果确认删除，则发送ajax请求
            $.ajax({
                type:"post",
                url:ctx+ "/user/deletecancellation?userNumber="+userNumber,
                data:'',
                dataType:"json",
                success:function(resultInfo) { //resultInfo回是调函数,用来接收后台返回的数据
                    console.log(resultInfo);
                    // 判断是否成功
                    if (resultInfo.code == 200) {
                        layer.msg("删除成功！",{icon:6});
                        //2.清除cookie
                        $.removeCookie("userId",{domain:"localhost",path:"/cispc"});
                        $.removeCookie("userNumber",{domain:"localhost",path:"/cispc"});
                        $.removeCookie("password",{domain:"localhost",path:"/cispc"});
                        $.removeCookie("trueName",{domain:"localhost",path:"/cispc"});
                        //3.跳转到登陆页面
                        window.parent.location.href=ctx+"/index"
                    } else {
                        //2.跳转到登陆页面
                        window.location.href = ctx+"/main";
                    }
                }
            })

        });
    });


});