package com.gmail.salahub.nikolay.springbootmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gmail.salahub.nikolay.jd2_project_week3.repository",
        "com.gmail.salahub.nikolay.jd2_project_week3.service",
        "com.gmail.salahub.nikolay.springbootmodule"}
)
public class SpringBootModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootModuleApplication.class, args);
    }
}
