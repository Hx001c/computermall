package com.athx.computermall.service.Impl;

import ch.qos.logback.core.joran.action.NewRuleAction;
import com.athx.computermall.bean.User;
import com.athx.computermall.mapper.UserMapper;
import com.athx.computermall.service.IUserService;
import com.athx.computermall.service.exception.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


/**
 * @author ：Hexin
 * @date ：Created in 2022/3/12 14:50
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 注册用户
     *
     * @param user
     */
    @Override
    public void userRegist(User user) {
        //判断用户名是否已占用
        if (userMapper.selectByUserName(user.getUsername()) != null) {
            //已经被占用,抛出UsernameDuplicateException异常
            throw new UsernameDuplicateException("用户名[" + user.getUsername() + "]已被占用");
        } else {
            //第一次注册
            // 创建当前时间对象

            Date date = new Date();
            // 补全数据：盐值
            String salt = UUID.randomUUID().toString().toUpperCase();
            user.setSalt(salt);
            // 补全数据：isDelete(0) 数据库默认是0
            // 补全数据：4项日志属性
            user.setCreatedUser(user.getUsername());
            user.setCreatedTime(date);
            user.setModifiedUser(user.getUsername());
            user.setModifiedTime(date);
            // 补全数据：加密后的密码
            String oldpassword = user.getPassword();
            user.setPassword(getMd5Password(oldpassword, salt));
            //保存到数据库
            int insert = userMapper.insert(user);
            //插入失败
            if (insert <= 0) {
                throw new InsertException("插入时出现未知异常");
            }
        }
    }

    @Override
    public User login(String username, String password) {
        //查询用户是否存在
        User user = userMapper.selectByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户数据不存在");
        }
        //检测用户的密码是否匹配
        //1.获取到查询的密码
        String selectPassword = user.getPassword();
        //2.获取到用户的盐值
        String salt = user.getSalt();
        //3.将用户传递过来的密码使用相同的规则进行加密
        String inputPassword = getMd5Password(password, salt);
        //4.进行匹配
        if (!selectPassword.equals(inputPassword)) {
            throw new PasswordNotMatchException("密码匹配失败");
        }
        //数据中转 减少传递参数 提高效率
        User newUser = new User();
        newUser.setUid(user.getUid());
        newUser.setUsername(user.getUsername());
        newUser.setAvatar(user.getAvatar());
        return newUser;

    }

    /**
     * 修改密码
     * @param uid 用户id
     * @param username 用户名
     * @param oldPassword 输入的老密码
     * @param newPassword  输入的新密码
     */
    @Override
    public void ModifyPassword(Integer uid,String username,String oldPassword,String newPassword) {
        //查询用户是否存在
        User user = userMapper.selectById(uid);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //检查输入的旧密码是否数据库中匹配相同
            //1.获取到查询的密码
            String selectPassword = user.getPassword();
            //2.获取到用户的盐值
            String salt = user.getSalt();
            System.out.println(selectPassword);

        //3.将用户传递过来的密码使用相同的规则进行加密
            String inputPassword = getMd5Password(oldPassword, salt);
            //4.进行匹配
            if (!selectPassword.equals(inputPassword)) {
                throw new PasswordNotMatchException("密码匹配失败");
            }
        //老密码匹配成功,将新的密码进行加密保存到数据库中\
        String md5NewPassword = getMd5Password(newPassword, salt);


        //调用userMapper中的方法进行修改
        int rows = userMapper.updatePasswordByUid(uid, md5NewPassword, username, new Date());
        if(rows<=0){
            throw new UpdateException("更新时出现异常");
        }
    }

    /**
     * 查询用户信息
     * @param id
     * @return
     */
    public User getById(Integer id) {
        User selectuser = userMapper.selectById(id);
        if(selectuser==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        User user = new User();
        user.setUsername(selectuser.getUsername());
        user.setPhone(selectuser.getPhone());
        user.setEmail(selectuser.getEmail());
        user.setGender(selectuser.getGender());
        return user;
    }

    @Override
    public void updateInfoByUserid(Integer uid, String username, User user) {

        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        User select = userMapper.selectById(uid);
        if(select==null){
            // 判断查询结果是否为null
            // 是：抛出UserNotFoundException异常
            throw new UsernameNotFoundException("用户不存在");
        }
        // 向参数user中补全数据：uid
        user.setUid(uid);
        // 向参数user中补全数据：modifiedTime(new Date())
        user.setModifiedTime(new Date());
        // 向参数user中补全数据：modifiedUser(username)
        user.setModifiedUser(username);
        // 调用userMapper的updateInfoByUid(User user)方法执行修改，并获取返回值
        int row = userMapper.updateInfoByUid(user);
        // 判断以上返回的受影响行数是否不为1
        // 是：抛出UpdateException异常
        if(row<=0){
            throw new UpdateException("更新时出现的异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User user = userMapper.selectById(uid);
        //判断用户是否存在
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        int rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if(rows<=0){
            throw new UpdateException("更新用户头像时出现异常");
        }
    }

    public static String getMd5Password(String password, String salt) {
        password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        return password;
    }

}
