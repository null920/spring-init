package com.light.springinit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.light.springinit", exclude = {RedisAutoConfiguration.class})
@MapperScan("com.light.springinit.mapper")
public class SpringInitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringInitApplication.class, args);
    }

}
