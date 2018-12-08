package com.dczd.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: com.dczd.study
 * @description: StudyApplication
 * @author: hou yangkun
 * @create: 2018-11-29
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.dczd.common","com.dczd.study"})
@MapperScan(basePackages = "com.dczd.study.mapper")
@EnableFeignClients(basePackages = {"com.dczd.common.client"})
public class StudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }
}
