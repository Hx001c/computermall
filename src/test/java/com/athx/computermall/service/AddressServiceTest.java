package com.athx.computermall.service;

import com.athx.computermall.bean.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 19:37
 * @description：
 * @modified By：
 * @version: $
 */
@SpringBootTest
public class AddressServiceTest {
    @Autowired
    private IAddressService addressService;
    @Test
    public void testInsert(){
        Address address = new Address();
        address.setPhone("11111111111");
        address.setName("小样");
        addressService.addNewAddress(address,1,"admin");
    }
}
