package com.education.service.base.entity;

import com.education.service.base.entity.enums.Response;
import org.apache.commons.lang3.StringUtils;

import static com.education.service.base.entity.enums.ResponseEnum.FAIL;
import static com.education.service.base.entity.enums.ResponseEnum.SUCCESS;

/**
 * @author ALazyDogXD
 * @date 2021/9/20 15:53
 * @description 响应
 */

public class ResponseMsg extends ResponseBase {

    private String msg;

    public ResponseMsg(int code, String msg) {
        super.code = code;
        this.msg = msg;
    }

    public static ResponseMsg success() {
        return resp(SUCCESS);
    }

    public static ResponseMsg success(String msg) {
        return resp(SUCCESS, msg);
    }

    public static ResponseMsg fail() {
        return resp(FAIL);
    }

    public static ResponseMsg fail(String msg) {
        return resp(FAIL, msg);
    }

    public static ResponseMsg resp(Response response) {
        return resp(response, null);
    }

    public static ResponseMsg resp(Response response, String msg) {
        int code = response.getCode();
        if (StringUtils.isBlank(msg)) {
            msg = response.getMsg();
        }
        return resp(code, msg);
    }

    public static ResponseMsg resp(int code, String msg) {
        return new ResponseMsg(code, msg);
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

}
