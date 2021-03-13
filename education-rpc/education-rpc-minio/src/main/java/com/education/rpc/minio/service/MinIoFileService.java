package com.education.rpc.minio.service;

/**
 * @author Mr_W
 * @date 2021/3/13 12:39
 * @description: MinIo 文件服务
 */
public interface MinIoFileService {

    /**
     * 上传缩略图
     *
     * @param bucketName  桶名称
     * @param contentType 媒体类型
     * @param path        文件路径
     * @param fileName    文件名称
     * @param imageByte   图片字节数组
     * @return 图片路径
     */
    String uploadThumbnail(String bucketName, String contentType, String path, String fileName, byte[] imageByte);

    /**
     * 删除文件
     *
     * @param bucketName 桶名称
     * @param path       文件路径(包含文件名)
     */
    void removeFile(String bucketName, String path);

}
