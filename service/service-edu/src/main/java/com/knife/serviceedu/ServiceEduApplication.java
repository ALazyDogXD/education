package com.knife.serviceedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Mr_W
 * @date 2021/2/16 11:46
 */
@SpringBootApplication(scanBasePackages = {"com.knife", "com.knife.serviceedu"})
public class ServiceEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class, args);
    }

}
