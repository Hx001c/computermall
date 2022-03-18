package com.athx.computermall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.athx.computermall.mapper")
public class ComputermallApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputermallApplication.class, args);
    }

}
