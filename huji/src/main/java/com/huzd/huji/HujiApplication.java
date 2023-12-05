package com.huzd.huji;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//扫描mapper包
@MapperScan("com.huzd.huji.mapper")
@EntityScan("com.huzd.huji.pojo")
public class HujiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HujiApplication.class, args);
    }

}
