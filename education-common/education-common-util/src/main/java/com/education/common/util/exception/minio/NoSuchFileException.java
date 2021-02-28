package com.education.common.util.exception.minio;

/**
 * @author Mr_W
 * @date 2021/2/27 18:57
 * @description 未找到文件
 */
public class NoSuchFileException extends Exception {

    public NoSuchFileException(String message) {
        super(message);
    }

}
