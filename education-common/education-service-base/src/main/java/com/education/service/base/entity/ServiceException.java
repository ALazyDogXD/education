package com.education.service.base.entity;

import com.education.service.base.entity.enums.Response;
import com.education.service.base.entity.enums.ResponseEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ALazyDogXD
 * @date 2021/9/20 16:27
 * @description 服务异常
 */

public class ServiceException extends RuntimeException implements Response {

    private final ResponseEnum responseEnum;

    private String alert;

    public ServiceException(ResponseEnum responseEnum, String message) {
        super(message);
        this.responseEnum = responseEnum;
        alert = message;
    }

    public ServiceException(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

    public ServiceException(ResponseEnum responseEnum, String message, Throwable cause) {
        super(message, cause);
        this.responseEnum = responseEnum;
        alert = message;
    }

    public ServiceException(ResponseEnum responseEnum, Throwable cause) {
        super(cause);
        this.responseEnum = responseEnum;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

    @Override
    public int getCode() {
        return responseEnum.getCode();
    }

    @Override
    public String getMsg() {
        if (StringUtils.isNotBlank(alert)) {
            return alert;
        } else {
            return responseEnum.getMsg();
        }
    }

}
