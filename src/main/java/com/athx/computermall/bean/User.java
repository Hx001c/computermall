package com.athx.computermall.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/12 10:37
 * @description：用户实体类
 * @modified By：
 * @version: $
 */
@Data
@TableName("t_user")
public class User extends BaseEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer uid;// '用户id',
    private String username;//'用户名',
    private String password;//密码
    private String salt;//盐值
    private String phone;//电话号码
    private String email;//邮箱
    private Integer gender;//性别 0:女性 1:男性
    private String avatar;//头像
    @TableLogic
    private Integer isDelete;//是否逻辑删除 0:未删除 1:已删除
//

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                ", isDelete=" + isDelete +'\''+
                "createdUser='" + super.getCreatedUser() + '\'' +
                ", createdTime=" + super.getCreatedTime()+
                ", modifiedUser='" + super.getModifiedUser() + '\'' +
                ", modifiedTime=" + super.getModifiedTime() +
                '}';
    }
}
