package com.awei.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author AWEI
 * @version 1.0
 * @description: 启动类
 * @date 2022/3/13 21:01
 */
@SpringBootApplication
@MapperScan("com.awei.server.mapper")
@EnableScheduling
public class YebApplication {

    public static void main(String[] args){
        SpringApplication.run(YebApplication.class);
    }

}

