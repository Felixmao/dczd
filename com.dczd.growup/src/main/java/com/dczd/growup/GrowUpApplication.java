package com.dczd.growup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: com.dczd.growup
 * @description: GrowUpApplication
 * @author: hou yangkun
 * @create: 2018-11-29
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.dczd.common","com.dczd.growup"})
@MapperScan(basePackages = "com.dczd.growup.mapper")
public class GrowUpApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrowUpApplication.class, args);
    }
}
