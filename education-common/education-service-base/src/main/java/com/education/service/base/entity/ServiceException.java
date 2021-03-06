package com.education.service.base.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ALazyDogXD
 * @date 2021/9/20 16:27
 * @description 服务异常
 */

public class ServiceException extends RuntimeException implements Response {

    private final Response response;

    private String alert;

    public ServiceException(Response response, String message) {
        super(message);
        this.response = response;
        alert = message;
    }

    public ServiceException(Response response) {
        this.response = response;
    }

    public ServiceException(Response response, String message, Throwable cause) {
        super(message, cause);
        this.response = response;
        alert = message;
    }

    public ServiceException(Response response, Throwable cause) {
        super(cause);
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public int getCode() {
        return response.getCode();
    }

    @Override
    public String getMsg() {
        if (StringUtils.isNotBlank(alert)) {
            return alert;
        } else {
            return response.getMsg();
        }
    }

}
