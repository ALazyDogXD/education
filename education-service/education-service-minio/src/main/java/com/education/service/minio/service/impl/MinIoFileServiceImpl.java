package com.education.service.minio.service.impl;

import com.education.rpc.minio.service.MinIoFileService;
import com.education.service.base.entity.ServiceException;
import com.education.service.minio.util.MinIoUtil;
import io.minio.errors.*;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static com.education.service.base.entity.enums.ResponseEnum.FILE_DEL_FAIL;
import static com.education.service.base.entity.enums.ResponseEnum.FILE_UPLOAD_FAIL;

/**
 * @author Mr_W
 * @date 2021/3/13 14:07
 * @description MinIo 文件服务
 */
@DubboService
public class MinIoFileServiceImpl implements MinIoFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinIoFileServiceImpl.class);

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.port}")
    private int port;

    @Override
    public String upload(String bucketName, String contentType, String path, String fileName, byte[] fileByte) {
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        try (InputStream in = new ByteArrayInputStream(fileByte)) {
            // 上传文件
            MinIoUtil.upload(bucketName, path + fileName, in, contentType);
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InsufficientDataException | InternalException | NoResponseException | InvalidBucketNameException | XmlPullParserException | ErrorResponseException | RegionConflictException | InvalidArgumentException | InvalidPortException | InvalidEndpointException e) {
            throw new ServiceException(FILE_UPLOAD_FAIL, e);
        }
        return endpoint + ":" + port + "/" + bucketName + "/" + path + fileName;
    }

    @Override
    public String upload(String bucketName, String contentType, String path, String fileName, InputStream in) {
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        try {
            // 上传文件
            MinIoUtil.upload(bucketName, path + fileName, in, contentType);
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InsufficientDataException | InternalException | NoResponseException | InvalidBucketNameException | XmlPullParserException | ErrorResponseException | RegionConflictException | InvalidArgumentException | InvalidPortException | InvalidEndpointException e) {
            throw new ServiceException(FILE_UPLOAD_FAIL, e);
        }
        return endpoint + ":" + port + "/" + bucketName + "/" + path + fileName;
    }

    @Override
    public String upload(String bucketName, String path, String fileName, byte[] fileByte) {
        return upload(bucketName, "application/octet-stream", path, fileName, fileByte);
    }

    @Override
    public void remove(String bucketName, String path) {
        try {
            MinIoUtil.removeFile(bucketName, path);
        } catch (InvalidPortException | InvalidEndpointException | IOException | InvalidKeyException | NoSuchAlgorithmException | InsufficientDataException | InternalException | NoResponseException | InvalidBucketNameException | XmlPullParserException | ErrorResponseException e) {
            throw new ServiceException(FILE_DEL_FAIL, e);
        }
    }
}
