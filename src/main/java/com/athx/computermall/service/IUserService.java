package com.athx.computermall.service;

import com.athx.computermall.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/12 14:27
 * @description：User类的业务逻辑
 * @modified By：
 * @version: $
 */
public interface IUserService extends IService<User> {
    /**
     * 注册
     * @param user
     */
    public void userRegist(User user);

    /**
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 登录成功返回对应的对象 登录失败返回null
     */
    public User login(String username,String password);

    /**
     * 修改密码
     * @param uid
     */
    public void ModifyPassword(Integer uid,String username,String oldPassword,String newPassword);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public User getById(Integer id);

    /**
     * 更新用户信息
     * @param uid
     * @param username
     * @param user
     * @return
     */
    public void updateInfoByUserid(Integer uid,String username,User user);

    /**
     * 更改用户头像
     * @param uid
     * @param avatar
     * @param username
     */
    public void changeAvatar(Integer uid,String avatar,String username);

}
