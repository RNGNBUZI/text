package com.wsz.text;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.wsz.text.dao")
@EnableTransactionManagement //启动事务
public class TextApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextApplication.class, args);
    }

}
