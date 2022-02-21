package com.education.service.sms.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import com.education.service.base.entity.ServiceException;
import com.education.service.base.service.RedisService;
import com.education.service.sms.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.education.service.base.entity.enums.ResponseEnum.SEND_AUTH_CODE_FAIL;

/**
 * @author ALazyDogXD
 * @date 2022/2/21 21:53
 * @description 短信服务
 */

@Service
public class SmsServiceImpl implements SmsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Value("${sms.access-key-id}")
    private String accessKeyId;

    @Value("${sms.access-key-secret}")
    private String accessKeySecret;

    @Value("${sms.endpoint}")
    private String endpoint;

    @Value("${sms.auth-code.sign-name}")
    private String signName;

    @Value("${sms.auth-code.template-code}")
    private String templateCode;

    @Value("${redis.key.auth-code.prefix}")
    private String redisKeyAuthCodePrefix;

    @Value("${redis.key.auth-code.expire.value}")
    private long redisKeyAuthCodeExpireValue;

    @Value("${redis.key.auth-code.expire.unit}")
    private String redisKeyAuthCodeExpireUnit;

    @Value("${redis.key.auth-code.length}")
    private int redisKeyAuthCodeLength;

    @Lazy
    @Resource
    private RedisService redisService;

    private Client client;

    @PostConstruct
    private void init() {
        try {
            client = getClient();
        } catch (Exception e) {
            LOGGER.error("短信服务客服端初始化失败", e);
        }
    }

    @Override
    public void sendAuthCode(String phone) {
        String authCode = getAuthCode(phone);
        try {
            if (client == null) {
                client = getClient();
            }
            // 发送验证码
            client.sendSms(new SendSmsRequest()
                    .setPhoneNumbers(phone)
                    .setSignName(signName)
                    .setTemplateCode(templateCode)
                    .setTemplateParam(authCode));
        } catch (Exception e) {
            throw new ServiceException(SEND_AUTH_CODE_FAIL);
        }
    }

    private String getAuthCode(String phone) {
        // 生成验证码
        StringBuilder authCode = new StringBuilder();
        for (int i = 0; i < redisKeyAuthCodeLength; i++) {
            authCode.append((int) (Math.random() * 10));
        }
        // 储存验证码到 Redis
        redisService.setEx(redisKeyAuthCodePrefix + phone,
                authCode,
                redisKeyAuthCodeExpireValue,
                TimeUnit.valueOf(redisKeyAuthCodeExpireUnit));
        return authCode.toString();
    }

    private Client getClient() throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        config.endpoint = endpoint;
        return new Client(config);
    }

}
