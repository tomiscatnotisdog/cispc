layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie','form','jquery'], function () {
        var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    /**
     * 表单提交的监听(登陆按钮)
     *    form.on('submit(按钮的lay-filter属性值)', function(data){}
     */
    form.on('submit(login)', function(data){
        console.log(data.field); //当前容器的全部表单字段，名值对形式：{name: value}

        //1.进行表单数据的非空校验-->LayUI中在输入框中加入lay-verify="required"属性,会自动坐非空校验

        //2.发送ajax请求,传递用户名和密码,执行登陆操作
        $.ajax({
            type:"post",
            url: ctx +"/user/login",
            data:{
                //userName与后台一致，username与index.ftl一致
                userNumber:data.field.usernumber,
                password:data.field.pwd,
                captcha:data.field.capt
            },
            dataType:"json",
            success:function(resultInfo){ //resultInfo回是调函数,用来接收后台返回的数据

                console.log(resultInfo);

                //判断返回的信息,如果code是200则表示登陆成功,否则失败
                if (200===resultInfo.code){
                    //登陆成功
                    /**
                     * 设置用户的登陆状态  保存用户的信息
                     *      session:服务器关闭销毁  cookie:客户端关闭销毁
                     */
                    layer.msg("登陆成功",function () {

                        //判断用户是否选择记住我
                        if ($("#rememberMe").prop("checked")){
                            //选择,设置cookie对象7天生效
                            //1.将用户信息存到cookie中
                            $.cookie("userId",resultInfo.result.userId,{expires:7});
                            $.cookie("userNumber",resultInfo.result.userNumber,{expires:7});
                            $.cookie("password",resultInfo.result.password,{expires:7});
                            $.cookie("trueName",resultInfo.result.trueName,{expires:7});
                        }else {
                            //1.将用户信息存到cookie中
                            $.cookie("userId",resultInfo.result.userId);
                            $.cookie("userNumber",resultInfo.result.userNumber);
                            $.cookie("password",resultInfo.result.password);
                            $.cookie("trueName",resultInfo.result.trueName);
                        }
                        //2.跳转到登陆页面
                        window.location.href = ctx+"/main";
                    })
                }else {
                    //登陆失败
                    layer.msg(resultInfo.msg,{icon:5})
                }
            }
        });

        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
});