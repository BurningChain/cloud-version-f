package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@MapperScan(basePackages = {"com.miracle.cloud.mapper"})
public class WebApplicationServerApp {

    public static void main(String[] args){
        SpringApplication.run(WebApplicationServerApp.class, args);
    }
}
