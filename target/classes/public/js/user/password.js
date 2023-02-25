layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    form.on("submit(saveBtn)",function (data){
        console.log(data.field)

        $.ajax({
            type:"post",
            url: ctx + "/user/updateUserPwd",
            data:{
                oldPwd: data.field.old_password,
                newPwd: data.field.new_password,
                confirmPwd: data.field.again_password
            },
            success: function (result) {
                if (result.code == 200){
                    layer.msg("⽤户密码修改成功，系统将在3秒钟后退出...",function () {
                        //清除cookie
                        $.removeCookie("userId", {domain:"localhost",path:"/crm"});
                        $.removeCookie("userNumber", {domain:"localhost",path:"/crm"});
                        $.removeCookie("password", {domain:"localhost",path:"/crm"});
                        window.parent.location.href = ctx + "/index";
                    });
                }else {
                    layer.msg(result.msg,{icon:5});
                }
            }
        });
    });

});