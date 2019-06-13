package com.yw.shirotemplate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yw.shirotemplate.*.mapper")
public class ShiroTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroTemplateApplication.class, args);
    }

}
