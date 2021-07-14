package com.education.service.edu.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr_W
 */
@Configuration("edu-mybatis-plus-configuration")
@MapperScan("com.education.service.edu.mapper")
public class MybatisPlusConfiguration {

    @Bean
    public GlobalConfig globalConfig(MetaHandler metaHandler) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(metaHandler);
        return globalConfig;
    }

    @Bean
    public MetaHandler metaHandler() {
        return new MetaHandler();
    }

}
