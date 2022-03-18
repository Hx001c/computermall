package com.athx.computermall.service;

import com.athx.computermall.bean.Cart;
import com.athx.computermall.mapper.CartMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.PrintConversionEvent;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/16 19:15
 * @description：
 * @modified By：
 * @version: $
 */
@SpringBootTest
public class cartTest {
    @Autowired
    private CartMapper cartMapper;
    @Test
    void test1(){
        Cart cart = new Cart();
        cart.setCid(1);
        cart.setNum(1);
        cart.setUid(4);
        cart.setPrice((long) 1999);
        cartMapper.insertOne(cart);
    }
}
