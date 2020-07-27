package com.hjy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hjy.system.dao")
@SpringBootApplication
public class YwjgSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(YwjgSystemApplication.class, args);
    }

}
