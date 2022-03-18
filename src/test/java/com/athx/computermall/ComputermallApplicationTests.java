package com.athx.computermall;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class ComputermallApplicationTests {
    @Autowired
    private DruidDataSource dataSource;
    @Test
    void contextLoads() {
    }

    @Test
    void getConnection() {
        System.out.println(dataSource);
    }
}
