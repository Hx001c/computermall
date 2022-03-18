package com.athx.computermall.mapper;

import com.athx.computermall.bean.Product;

import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/15 19:12
 * @description：处理商品数据的持久层接口
 * @modified By：
 * @version: $
 */
public interface ProductMapper {
    List<Product> selectList();

    Product selectById(Integer id);
}
