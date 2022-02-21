package com.education.service.sms.service;

/**
 * @author ALazyDogXD
 * @date 2022/2/21 21:53
 * @description 短信服务
 */

public interface SmsService {

    /**
     * 发送验证码
     * @param phone 手机号
     */
    void sendAuthCode(String phone);

}
