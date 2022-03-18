package com.athx.computermall;

import com.athx.computermall.bean.Address;
import com.athx.computermall.mapper.AddressMapper;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 19:05
 * @description：
 * @modified By：
 * @version: $
 */
@SpringBootTest
public class addressTest {

    @Autowired
    private AddressMapper addressMapper;
    @Test
    public void insertAddress(){
        Address address = new Address();
        address.setUid(1);
        address.setName("小何");
        addressMapper.insert(address);
    }
    @Test
    public void testGetList(){

    }
}
