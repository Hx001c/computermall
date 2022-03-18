package com.athx.computermall.Vo;

import lombok.Data;

import java.awt.print.PrinterGraphics;
import java.io.Serializable;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/16 21:30
 * @description：展示用的商品数据
 * @modified By：
 * @version: $
 */
@Data
public class CartVo implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Integer num;
    private long price;
    private String title;
    private String image;
    private long realPrice;

}
