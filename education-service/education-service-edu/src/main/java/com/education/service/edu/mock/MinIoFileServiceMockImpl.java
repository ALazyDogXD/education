package com.education.service.edu.mock;

import com.education.rpc.minio.service.MinIoFileService;
import com.education.service.base.entity.ServiceException;

import static com.education.service.base.entity.enums.ResponseEnum.FILE_DEL_FAIL;
import static com.education.service.base.entity.enums.ResponseEnum.FILE_UPLOAD_FAIL;

/**
 * @author Mr_W
 * @date 2021/3/14 1:16
 * @description MinIo 文件服务降级
 */
public class MinIoFileServiceMockImpl implements MinIoFileService {
    @Override
    public String upload(String bucketName, String contentType, String path, String fileName, byte[] fileByte) {
        throw new ServiceException(FILE_UPLOAD_FAIL);
    }

    @Override
    public String upload(String bucketName, String path, String fileName, byte[] fileByte) {
        throw new ServiceException(FILE_UPLOAD_FAIL);
    }

    @Override
    public void remove(String bucketName, String path) {
        throw new ServiceException(FILE_DEL_FAIL);
    }
}
