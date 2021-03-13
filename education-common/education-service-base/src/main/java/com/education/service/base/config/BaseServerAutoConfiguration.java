package com.education.service.base.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr_W
 * @date 2021/2/27 19:44
 * @description 基础服务配置
 */
@ComponentScan({"com.education.service.base", "com.education.common.util"})
@Configuration
public class BaseServerAutoConfiguration {
}
