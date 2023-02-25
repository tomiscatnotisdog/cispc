package com.xxxx.cispc.service;

import com.xxxx.cispc.base.BaseService;
import com.xxxx.cispc.dao.UserMapper;
import com.xxxx.cispc.exceptions.ParamsException;
import com.xxxx.cispc.model.UserModel;
import com.xxxx.cispc.utils.AssertUtil;
import com.xxxx.cispc.utils.Md5Util;
import com.xxxx.cispc.utils.UserIDBase64;
import com.xxxx.cispc.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.TypeDiscriminator;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserService extends BaseService<User,Integer> {
    @Resource
    private UserMapper userMapper;
    @Resource
    private IdentityMoudleService identityMoudleService;

    /**
     * ⽤户登录
     * 1. 验证参数
     * 学号 ⾮空判断
     * 密码 ⾮空判断
     * 图形验证 ⾮空判断
     * 2. 根据学号，查询学号对象
     * 3. 判断学号是否存在
     * 学号对象为空，记录不存在，⽅法结束
     * 4. 学号对象不为空
     * 学号存在，校验密码
     * 密码不正确，⽅法结束
     * 5. 密码正确
     * 图形验证不正确，方法结束
     * 6.图形验证正确
     * 学号登录成功，返回学号的相关信息 （定义UserModel类，返回学号某些信息）
     */
    public UserModel userLogin(String userNumber, String password , String captcha , String nickName,String trueName){
        //1、登入校验
        checkLoginParams(userNumber,password,captcha);
        //2. 根据学号，查询学号对象
        User user =userMapper.queryUserByName(userNumber);
        //3. 判断学号是否存在
        //学号对象为空，记录不存在，⽅法结束
        AssertUtil.isTrue(null == user,"学号不存在或已注销！");
        //4. 学号对象不为空（⽤户存在，校验密码。密码不正确，⽅法结束）
        checkLoginPwd(password,user.getPassword());

        //5. 密码正确
        //图形验证不正确，方法结束
        if (!captcha.equals("xszg") ){
            throw new ParamsException("图形验证不正确!");
        }
        //6.图形验证正确
        //⽤户登录成功，返回⽤户的相关信息 （定义UserModel类，返回⽤户某些信息）
        UserModel userModel = new UserModel();
        userModel.setUserId(UserIDBase64.encoderUserID(user.getId()));
        //id解密
        /*String s = UserIDBase64.encoderUserID(user.getId());
        System.out.println(s);
        System.out.println(UserIDBase64.decoderUserID(s));*/
        userModel.setUserNumber(user.getUserNumber());
        userModel.setPassword(user.getPassword());
        userModel.setCaptcha(user.getCaptcha());
        userModel.setNickName(user.getNickName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }
    //4. 学号对象不为空（⽤户存在，校验密码。密码不正确，⽅法结束）
    private void checkLoginPwd(String password, String password1) {

        //学号存在，校验密码
        // ⽐较密码
        //密码不正确，⽅法结束
        AssertUtil.isTrue(!password.equals(password1),"密码不正确！");
    }

    //1、登入校验
    private void checkLoginParams(String userNumber, String password, String captcha) {
        //学号 ⾮空判断
        AssertUtil.isTrue(null == userNumber,"学号不能为空！");
        //密码 ⾮空判断
        AssertUtil.isTrue(StringUtils.isBlank(password),"密码不能为空！");
        //图形验证 ⾮空判断
        AssertUtil.isTrue(StringUtils.isBlank(captcha),"验证码不能为空!");
    }



    /**
     * ⽤户密码修改
     * 1. 参数校验
     * ⽤户ID：userId ⾮空 ⽤户对象必须存在
     * 原始密码：oldPassword ⾮空 与数据库中密⽂密码保持⼀致
     * 新密码：newPassword ⾮空 与原始密码不能相同
     * 确认密码：confirmPassword ⾮空 与新密码保持⼀致
     * 2. 设置⽤户新密码
     * 3. 执⾏更新操作
     * 受影响的⾏数⼩于1，则表示修改失败
     *
     * 注：在对应的更新⽅法上，添加事务控制
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserPwd(Integer userId,String oldPwd, String newPwd, String confirmPwd){
        // 1. 参数校验
        User user = (User) userMapper.selectByPrimaryKey(userId);

        checkPasswordParams(user, oldPwd, newPwd, confirmPwd);

        // 2. 设置⽤户新密码
        user.setPassword(newPwd);
        //3. 执⾏更新操作  受影响的⾏数⼩于1，则表示修改失败
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1,"修改失败！");

    }
    /** 验证⽤户密码修改参数
     * ⽤户ID：userId ⾮空 ⽤户对象必须存在
     * 原始密码：oldPassword ⾮空 与数据库中密⽂密码保持⼀致
     * 新密码：newPassword ⾮空 与原始密码不能相同
     * 确认密码：confirmPassword ⾮空 与新密码保持⼀致
     * */

    public void checkPasswordParams(User user,String oldPwd, String newPwd, String confirmPwd){
        //⽤户ID：userId ⾮空 ⽤户对象必须存在
        AssertUtil.isTrue(null == user ,"用户不存在！");
        //原始密码：oldPassword ⾮空 与数据库中密⽂密码保持⼀致
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd),"原始密码不能为空!");
        AssertUtil.isTrue(!oldPwd.equals(user.getPassword()),"原始密码不正确！");
        //新密码：newPassword ⾮空 与原始密码不能相同
        AssertUtil.isTrue(StringUtils.isBlank(newPwd),"新密码不能为空！");
        AssertUtil.isTrue(newPwd.equals(oldPwd),"新密码不能和原始密码一致！");
        //确认密码：confirmPassword ⾮空 与新密码保持⼀致
        AssertUtil.isTrue(StringUtils.isBlank(confirmPwd),"确认密码不能为空！");
        AssertUtil.isTrue(!confirmPwd.equals(newPwd),"确认密码与新密码不一致！");
    }

    /**基本资料
     * 1.参数查询
     * 通过Id查询基本信息
     * ⽤户ID：Id ⾮空 ⽤户对象必须存在
     * 2.设置参数
     * 3.执⾏更新操作
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    public User updateSetting(User user){

        // 判断用户ID是否为空，且数据存在
        AssertUtil.isTrue(null == user.getId(), "待更新记录不存在！");

        //1.参数校验
        //姓名 ⾮空判断
        AssertUtil.isTrue(StringUtils.isBlank(user.getTrueName()),"管理账号不能为空！");
        //手机 ⾮空判断
        AssertUtil.isTrue(null == user.getPhone(),"手机不能为空！");
        //学号 ⾮空判断
        AssertUtil.isTrue(null == user.getUserNumber(),"学号不能为空！");
        //昵称 ⾮空判断
        AssertUtil.isTrue(StringUtils.isBlank(user.getNickName()),"昵称不能为空!");
        //2.设置参数
        user.setUpdateDate(new Date());
        // 3.执⾏更新操作
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) != 1,"更新失败！");
        return user;

    }

    /**
     * 注册页面
     * 1.参数校验
     * 非空  判断
     * 2.设置参数
     * 3.执行插入数据
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    public User insertregister(User user){
        //1.参数校验  非空判断
        //学号
        System.out.println(user);
        AssertUtil.isTrue(null == user.getUserNumber(),"学号不能为空！");
        User temp = userMapper.selectByUserNumber(user.getUserNumber());
        AssertUtil.isTrue(temp != null,"学号已存在！");
        //真实姓名
        AssertUtil.isTrue(StringUtils.isBlank(user.getTrueName()),"真实姓名不能为空！");
        //昵称
        AssertUtil.isTrue(StringUtils.isBlank(user.getNickName()),"昵称不能为空！");
        //手机号
        AssertUtil.isTrue(null == user.getPhone(),"手机号不能为空！");
        //密码
        AssertUtil.isTrue(StringUtils.isBlank(user.getPassword()),"密码不能为空！");
        //身份
        AssertUtil.isTrue(StringUtils.isBlank(user.getIdentity()),"身份不能为空！");


        AssertUtil.isTrue(!(user.getIdentity().equals("教师")||user.getIdentity().equals("学生")),"请输入学生或教师！");
        //2.设置参数
        user.setIsValid(1);
        user.setUpdateDate(new Date());
        user.setCreateDate(new Date());
        //3.执行插入数据

        AssertUtil.isTrue(userMapper.insertSelective(user) != 1,"注册失败！");
        return user;
    }

    /**注销页面
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deletecancellation(String userNumber){
        //参数校验
        AssertUtil.isTrue(null == userNumber,"ID不能为空！");
        //2.设置参数
        //执行删除数据
        AssertUtil.isTrue(userMapper.delectuserNumber(userNumber) != 1,"注销账户失败！");

    }


    /**
     * 权限操作--通过用户学号获取身份,通过身份获取资源码
     * @param userNumber
     * @return
     */
    public List<Integer> queryCode(String userNumber) {
        //通过学号查身份
        String identity=userMapper.selectByUserNumber(userNumber).getIdentity();
        //通过身份查资源
        return identityMoudleService.queryCodeByIdentityName(identity);
    }
}
