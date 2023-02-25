layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie','form','jquery'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    /**
     * 表单提交的监听
     *    form.on('submit(按钮的lay-filter属性值)', function(data){}
     */
    form.on('submit(saveBtn)', function(data){
        console.log(data.field)

        $.ajax({
            type:"post",
            url: ctx + "/user/updateSetting",
            data:{
                id:data.field.id,
                trueName:data.field.truename,
                phone:data.field.phon,
                userNumber: data.field.usernumber,
                nickName:data.field.nickname,
                personalProfile:data.field.personalprofile
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
                    layer.msg("更新成功",function () {

                        //判断用户是否选择记住我
                        if ($("#rememberMe").prop("checked")){
                            //选择,设置cokkie对象7天生效
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
                    })
                }else {
                    //登陆失败
                    layer.msg(resultInfo.msg,{icon:5})
                }
            }
        });

        return false;
        });
});