package com.education.rpc.minio.service;

/**
 * @author Mr_W
 * @date 2021/3/13 12:39
 * @description: MinIo 文件服务
 */
public interface MinIoFileService {

    /**
     * 上传文件
     *
     * @param bucketName  桶名称
     * @param contentType 媒体类型
     * @param path        文件路径
     * @param fileName    文件名称
     * @param fileByte   文件字节数组
     * @return 图片路径
     */
    String upload(String bucketName, String contentType, String path, String fileName, byte[] fileByte);


    /**
     * 上传文件
     *
     * @param bucketName  桶名称
     * @param path        文件路径
     * @param fileName    文件名称
     * @param fileByte   文件字节数组
     * @return 图片路径
     */
    String upload(String bucketName, String path, String fileName, byte[] fileByte);

    /**
     * 删除文件
     *
     * @param bucketName 桶名称
     * @param path       文件路径(包含文件名)
     */
    void remove(String bucketName, String path);

}
