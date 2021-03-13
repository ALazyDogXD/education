package com.education.service.edu.mock;

import com.education.rpc.minio.service.MinIoFileService;
import com.education.service.base.entity.ServiceException;

/**
 * @author Mr_W
 * @date 2021/3/14 1:16
 * @description MinIo 文件服务降级
 */
public class MinIoFileServiceMock implements MinIoFileService {
    @Override
    public String uploadThumbnail(String bucketName, String contentType, String path, String fileName, byte[] imageByte) {
        throw ServiceException.serviceException("MinIoFileService.uploadThumbnail 调用失败").alertMessage("文件上传失败").build();
    }

    @Override
    public void removeFile(String bucketName, String path) {
        throw ServiceException.serviceException("MinIoFileService.removeFile 调用失败").alertMessage("文件删除失败").build();
    }
}
