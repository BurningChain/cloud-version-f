package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MiracleOauth2ServerApp {

    public static void main(String[] args){
        SpringApplication.run(MiracleOauth2ServerApp.class, args);
    }
}
