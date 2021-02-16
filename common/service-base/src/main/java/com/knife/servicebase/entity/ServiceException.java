package com.knife.servicebase.entity;

import com.knife.servicebase.enums.ServiceExceptionEnum;

/**
 * @author Mr_W
 * @date 2021/2/16 14:14
 * @description: 通用异常
 */
public class ServiceException extends RuntimeException {

    private static final long	serialVersionUID	= 8935805243057842722L;

    private Integer code;

    public ServiceException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message) {
        this(ServiceExceptionEnum.BUSINESS_FAIL.getCode(), message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
