package com.athx.computermall.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/15 19:07
 * @description：商品的实体类
 * @modified By：
 * @version: $
 */
@Data
public class Product extends BaseEntity implements Serializable {
    //商品id
    private Integer id;
    //分类id
    private Integer categoryId;
    //商品系类
    private String itemType;
    //商品标题
    private String title;
    //商品卖点
    private String sellPoint;
    //商品价格
    private Long price;
    //商品数量
    private Integer num;
    //图片路径
    private String image;
    //商品状态 1：上架   2：下架   3：删除',
    private Integer status;
    //商品优先级
    private Integer priority;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", itemType='" + itemType + '\'' +
                ", title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                "createdUser='" + super.getCreatedUser() + '\'' +
                ", createdTime=" + super.getCreatedTime()+
                ", modifiedUser='" + super.getModifiedUser() + '\'' +
                ", modifiedTime=" + super.getModifiedTime() +
                '}';
    }
}
