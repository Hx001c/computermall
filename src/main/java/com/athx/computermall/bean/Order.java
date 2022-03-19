package com.athx.computermall.bean;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/19 10:51
 * @description：订单数据的实体类
 * @modified By：
 * @version: $
 */
@Data
public class Order extends BaseEntity implements Serializable {
    //订单id
    private Integer oid;
    //用户id
    private Integer uid;
    //收货人姓名
    private String recvName;
    //收货人电话
    private String recvPhone;
    //收货人地区
    private String recvProvince;
    private String recvCity;
    private String recvArea;
    private String recvAddress;
    //总价
    private Long totalPrice;
    //状态：0-未支付，1-已支付，2-已取消，3-已关闭，4-已完成
    private Integer status;
    //下单时间
    private Date orderTime;
    //支付时间
    private Date payTime;
}
