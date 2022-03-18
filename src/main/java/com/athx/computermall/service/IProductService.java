package com.athx.computermall.service;

import com.athx.computermall.bean.Product;

import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/15 19:16
 * @description：商品类的业务层
 * @modified By：
 * @version: $
 */
public interface IProductService {
    /**
     * 查询热销商品的前四名
     * @return 热销商品前四名的集合
     */
    List<Product> findHotList();

    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    Product findById(Integer id);
}
