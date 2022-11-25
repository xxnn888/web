package com.awei.mail;

import com.awei.server.pojo.MailConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;


/**
 * @author AWEI
 * @version 1.0
 * @description: 启动类
 * @date 2022/3/13 21:01
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class);
    }

    @Bean
    public Queue queue() {
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }

}

