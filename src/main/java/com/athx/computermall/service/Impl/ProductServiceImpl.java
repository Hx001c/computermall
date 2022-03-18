package com.athx.computermall.service.Impl;

import com.athx.computermall.bean.Product;
import com.athx.computermall.mapper.ProductMapper;
import com.athx.computermall.service.IProductService;
import com.athx.computermall.service.exception.ProductNotFoundException;
import com.sun.scenario.effect.impl.prism.PrMergePeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/15 19:17
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> findHotList() {
        return productMapper.selectList();
    }

    @Override
    public Product findById(Integer id) {
        //查询数据是否存在
        Product product = productMapper.selectById(id);
        if(product==null){
            throw new ProductNotFoundException("商品不存在异常");
        }
        //设置不需要的属性为null
        product.setModifiedTime(null);
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        // 返回查询结果
        return product;
    }
}
