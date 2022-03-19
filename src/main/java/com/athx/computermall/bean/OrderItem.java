package com.athx.computermall.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/19 10:54
 * @description：订单中的商品数据
 * @modified By：
 * @version: $
 */
@Data
public class OrderItem  extends BaseEntity implements Serializable {
    //订单中的商品记录id
    private Integer id;
    //所归属订单的id
    private Integer oid;
    //商品id
    private Integer pid;
    //商品标题
    private String title;
    //商品图片
    private String image;
    //商品价格
    private Long price;
    //商品数量
    private Integer num;

}
