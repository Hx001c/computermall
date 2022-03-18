package com.athx.computermall.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/16 19:00
 * @description：购物车实体类
 * @modified By：
 * @version: $
 */
@Data
public class Cart  extends BaseEntity implements Serializable {
    //购物数据id
    private Integer cid;
    //用户id
    private Integer uid;
    //商品id
    private Integer pid;
    //商品价格
    private Long price;
    //商品数量
    private Integer num;

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", price=" + price +
                ", num=" + num +
                "createdUser='" + super.getCreatedUser() + '\'' +
                ", createdTime=" + super.getCreatedTime()+
                ", modifiedUser='" + super.getModifiedUser() + '\'' +
                ", modifiedTime=" + super.getModifiedTime() +
                '}';
    }
}
