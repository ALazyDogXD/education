package com.education.rpc.minio.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Mr_W
 * @date 2021/3/13 12:39
 * @description: MinIo 文件服务
 */
public interface MinIoFileService {

    /**
     * 上传缩略图
     *
     * @param bucketName 桶名称
     * @param image      图片文件
     * @param path       文件路径
     * @param fileName   文件名称
     */
    void uploadThumbnail(String bucketName, MultipartFile image, String path, String fileName);

    /**
     * 删除文件
     *
     * @param bucketName 桶名称
     * @param path       文件路径(包含文件名)
     */
    void removeFile(String bucketName, String path);

}
