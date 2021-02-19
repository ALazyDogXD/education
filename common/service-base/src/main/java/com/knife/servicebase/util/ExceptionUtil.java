package com.knife.servicebase.util;

import cn.hutool.core.util.StrUtil;
import com.knife.servicebase.entity.ServiceException;
import com.knife.servicebase.entity.ServiceExceptionBuilder;
import com.knife.servicebase.enums.ServiceExceptionEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author Mr_W
 * @date 2021/2/19 15:56
 * @description 异常工具
 */
public class ExceptionUtil {

    private ExceptionUtil() {
    }

    /**
     * 抛出业务异常
     *
     * @param message 日志记录信息
     * @param params  占位符参数(包含异常 e)
     */
    @Deprecated
    public static ServiceException buildServiceException(String message, Object... params) {
        Throwable cause;
        if (Objects.isNull(cause = getThrowable(params))) {
            return new ServiceException(StrUtil.format(message, getParams(params)));
        }
        return new ServiceException(ServiceExceptionEnum.BUSINESS_FAIL.getCode(), StrUtil.format(message, getParams(params)), cause);
    }

    /**
     * 获取除异常外的参数
     *
     * @param params 参数
     * @return 除异常外的参数
     */
    private static Object[] getParams(Object... params) {
        if (params.length != 0 && params[params.length - 1] instanceof Throwable) {
            Object[] paramsCopy = new Object[params.length - 1];
            System.arraycopy(params, 0, paramsCopy, 0, params.length - 1);
            return paramsCopy;
        }
        return params;
    }

    /**
     * 获取异常
     *
     * @param params 参数
     * @return 异常
     */
    private static Throwable getThrowable(Object... params) {
        if (params.length != 0 && params[params.length - 1] instanceof Throwable) {
            return (Throwable) params[params.length - 1];
        }
        return null;
    }

    /**
     * 获取业务异常构造器, code 默认 500
     * 已弃用, 请使用 ServiceException 的静态方法直接创建
     *
     * @param message 异常信息
     * @param cause   原始异常
     * @return 业务异常构造器
     */
    @Deprecated
    public static ServiceExceptionBuilder serviceException(String message, Throwable cause) {
        if (StringUtils.isBlank(message)) {
            throw new RuntimeException("message of service exception can not be null");
        }
        return new ServiceExceptionBuilder(message, cause);
    }

    /**
     * 获取业务异常构造器
     * 已弃用, 请使用 ServiceException 的静态方法直接创建
     *
     * @param serviceExceptionEnum 业务异常枚举
     * @param cause                原始异常
     * @return 业务异常构造器
     */
    @Deprecated
    public static ServiceExceptionBuilder serviceException(ServiceExceptionEnum serviceExceptionEnum, Throwable cause) {
        if (Objects.isNull(serviceExceptionEnum)) {
            throw new RuntimeException("enum of service exception can not be null");
        }
        return new ServiceExceptionBuilder(serviceExceptionEnum, cause);
    }

}
