package com.knife.commonutil.util;

/**
 * @author Mr_W
 * @date 2021/2/16 13:48
 * @description: 封装返回值
 */
public class ResponseBean {

    private static final Integer	SUCC_CODE	= 200;

    private static final Integer	ERROR_CODE	= 500;

    private static final String 	ERROR_MSG   = "网络异常，请稍后再试";

    private String					msg;

    private Integer					code;

    private Object					data;

    private ResponseBean(String msg, Integer code, Object data) {
        super();
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    /**
     * 成功请求
     * @param msg 消息
     * @param code 响应码
     * @param data 数据
     * @return 封装返回值
     */
    public static ResponseBean succ(String msg, Integer code, Object data) {
        return new ResponseBean(msg, code, data);
    }

    /**
     * 请求成功
     * @param msg 消息
     * @param data 数据
     * @return 封装返回值
     */
    public static ResponseBean succ(String msg, Object data) {
        return ResponseBean.succ(msg, SUCC_CODE, data);
    }

    /**
     * 请求成功
     * @param msg 消息
     * @return 封装返回值
     */
    public static ResponseBean succ(String msg) {
        return ResponseBean.succ(msg, SUCC_CODE, null);
    }

    /**
     * 请求成功
     * @param data 数据
     * @return 封装返回值
     */
    public static ResponseBean succ(Object data) {
        return ResponseBean.succ(null, SUCC_CODE, data);
    }

    /**
     * 请求成功
     * @return 封装返回值
     */
    public static ResponseBean succ() {
        return ResponseBean.succ(null, SUCC_CODE, null);
    }

    /**
     * 请求失败
     * @param msg 信息
     * @param code 错误码
     * @param data 数据
     * @return 封装返回值
     */
    public static ResponseBean fail(String msg, Integer code, Object data) {
        return new ResponseBean(msg, code, data);
    }

    /**
     * 请求失败
     * @param msg 信息
     * @param data 数据
     * @return 封装返回值
     */
    public static ResponseBean fail(String msg, Object data) {
        return ResponseBean.fail(msg, ERROR_CODE, data);
    }

    /**
     * 请求失败
     * @param msg 信息
     * @return 封装返回值
     */
    public static ResponseBean fail(String msg) {
        return ResponseBean.fail(msg, ERROR_CODE, null);
    }

    /**
     * 请求失败
     * @param data 数据
     * @return 封装返回值
     */
    public static ResponseBean fail(Object data) {
        return ResponseBean.fail(null, ERROR_CODE, data);
    }

    /**
     * 请求失败
     * @return 封装返回值
     */
    public static ResponseBean fail() {
        return ResponseBean.fail(ERROR_MSG, ERROR_CODE, null);
    }

    /**
     * 请求失败
     * @param msg 信息
     * @param code 错误码
     * @return 封装返回值
     */
    public static ResponseBean fail(String msg, Integer code) {
        return ResponseBean.fail(msg, code, null);
    }

    /**
     * 请求失败
     * @param code 错误码
     * @param data 数据
     * @return 封装返回值
     */
    public static ResponseBean fail(Object data, Integer code) {
        return ResponseBean.fail(null, code, data);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
