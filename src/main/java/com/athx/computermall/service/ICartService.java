package com.athx.computermall.service;

import com.athx.computermall.Vo.CartVo;
import com.athx.computermall.bean.Cart;

import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/16 19:22
 * @description：购物车的业务层
 * @modified By：
 * @version: $
 */
public interface ICartService {
    /**
     * 添加到购物车
     * @param uid
     * @param pid
     * @param amount
     * @param username
     */
    void addCartItem(Integer uid, Integer pid, Integer amount, String username);

    /**
     * 获取商品列表
     * @param uid
     * @return
     */
    List<CartVo> getListCartVo(Integer uid);

    /**
     * 增加购物车商品的数量
     * @param cid
     * @param username
     * @param uid
     */
    public Integer addNum( Integer cid, String username,Integer uid);

    /**
     * 减少购物车商品的数量
     * @param cid
     * @param username
     * @param uid
     */
    public Integer reduceNum( Integer cid, String username,Integer uid);

    /**
     * 删除购物车的商品数据
     * @param cid
     * @param uid
     */
    void deleteCartItem(Integer cid,Integer uid);
}
