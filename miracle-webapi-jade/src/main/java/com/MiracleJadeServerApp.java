package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@RibbonClients
@EnableFeignClients(basePackages = {"com.miracle.cloud.client"})
public class MiracleJadeServerApp {

    public static void main(String[] args){
        SpringApplication.run(MiracleJadeServerApp.class, args);
    }
}
