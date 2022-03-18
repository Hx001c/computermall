package com.athx.computermall.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 18:35
 * @description：收获地址的实体类
 * @modified By：
 * @version: $
 */
@Data
@TableName("t_address")
public class Address extends BaseEntity implements Serializable {
    //收获id
    private Integer aid;
    //归属的用户id
    private Integer uid;
    //收获人的姓名
    private String name;
    //省-名称
    private String provinceName;
    //省-行政代号
    private String provinceCode;
    //市名称
    private String cityName;
    //市-行政代号
    private String cityCode;
    //区名称
    private String areaName;
    //区-行政代号
    private String areaCode;
    //'邮政编码',
    private String zip;
    //详细地址
    private String address;
    //电话
    private String phone;
    //固话
    private String tel;
    //标签
    private String tag;
    //是否默认 1默认 0 不默认
    private Integer isDefault;

    @Override
    public String toString() {
        return "Address{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", zip='" + zip + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", tel='" + tel + '\'' +
                ", tag='" + tag + '\'' +
                ", isDefault=" + isDefault +'\''+
                "createdUser='" + super.getCreatedUser() + '\'' +
                ", createdTime=" + super.getCreatedTime()+
                ", modifiedUser='" + super.getModifiedUser() + '\'' +
                ", modifiedTime=" + super.getModifiedTime() +
                '}';
    }
}
