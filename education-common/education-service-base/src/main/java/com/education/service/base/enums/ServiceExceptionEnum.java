package com.education.service.base.enums;

/**
 * @author Mr_W
 */

public enum ServiceExceptionEnum {
	/**
	 * 验证失败
	 */
	CHECK_FAIL(501, "验证失败"),
	/**
	 * 网络异常
	 */
	BUSINESS_FAIL(500),
	/**
	 * 请求参数错误
	 */
	INVALID_PARAMS(400, "请求参数错误"),
	/**
	 * 数据异常
	 */
	DATA_EXCEPTION(502, "数据异常");

	private int		code;

	private String	msg;

	ServiceExceptionEnum(int code) {
		this.code = code;
	}

	ServiceExceptionEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}
}
