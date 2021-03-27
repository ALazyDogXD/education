package com.education.service.minio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Mr_W
 * @date 2021/2/16 11:46
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceMinIoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMinIoApplication.class, args);
    }

}
