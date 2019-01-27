package io.lxx.checkweixin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.lxx.checkweixin.dao")
public class CheckweixinApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckweixinApplication.class, args);
    }

}

