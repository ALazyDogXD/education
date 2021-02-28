package com.education.common.util.exception.minio;

/**
 * @author Mr_W
 * @date 2021/2/18 10:29
 * @description 空图片异常
 */
public class EmptyImageException extends Exception {

    public EmptyImageException(String message) {
        super(message);
    }

}
