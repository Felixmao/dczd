package com.dczd.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: com.dczd.member
 * @description: MemberApplication
 * @author: hou yangkun
 * @create: 2018-11-27
 */
@EnableFeignClients(basePackages = {"com.dczd.common.client"})
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.dczd.common", "com.dczd.member"})
@MapperScan(basePackages = "com.dczd.member.mapper")
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }
}
