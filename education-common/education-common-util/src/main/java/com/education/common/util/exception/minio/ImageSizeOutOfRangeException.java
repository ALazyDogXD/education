package com.education.common.util.exception.minio;

/**
 * @author Mr_W
 * @date 2021/2/18 10:31
 * @description 图片大小超范围
 */
public class ImageSizeOutOfRangeException extends Exception {

    public ImageSizeOutOfRangeException(String message) {
        super(message);
    }

}
