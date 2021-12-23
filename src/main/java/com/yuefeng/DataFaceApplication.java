package com.yuefeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.yuefeng.dao")
public class DataFaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataFaceApplication.class, args);
    }
}
