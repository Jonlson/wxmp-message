package com.wth.msgmp_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication(scanBasePackages = {"com.wth.msgmp_api"})
@ServletComponentScan
public class MsgmpApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsgmpApplication.class, args);
    }
}
