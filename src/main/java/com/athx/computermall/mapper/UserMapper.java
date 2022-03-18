package com.athx.computermall.mapper;

import com.athx.computermall.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import javax.lang.model.element.NestingKind;
import java.util.Date;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/12 10:49
 * @description：
 * @modified By：
 * @version: $
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据username查询数据
     * @param userName
     * @return
     */
    public User selectByUserName(String userName);

    /**
     * 根据修改用户密码
     * @param uid
     * @param newPassword
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    public int updatePasswordByUid(Integer uid, String newPassword, String modifiedUser, Date modifiedTime);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public int updateInfoByUid(User user);

    /**
     * 更新用户头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    public int updateAvatarByUid(Integer uid,String avatar,String modifiedUser,Date modifiedTime);

}
