package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean//相当于applicationContext.xml ‹bean id="" class="",
    @LoadBalanced //使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
    //Ribbon和Eureka整合后Consumer可以直接调用服务而不用再关心地址和端口号，该服务还有负载功能了。
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
