package com.education.service.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Mr_W
 * @date 2021/4/4 15:16
 * @description 视频服务主类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceVidApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVidApplication.class, args);
    }

}
