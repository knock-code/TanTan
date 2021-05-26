package com.example.wxstc;

import com.example.wxstc.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class WxstcApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxstcApplication.class, args);
    }
}
