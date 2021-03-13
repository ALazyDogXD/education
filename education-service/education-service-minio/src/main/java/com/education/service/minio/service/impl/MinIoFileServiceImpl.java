package com.education.service.minio.service.impl;

import com.education.common.util.MinIoUtil;
import com.education.rpc.minio.service.MinIoFileService;
import com.education.service.base.entity.ServiceException;
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
    public String uploadThumbnail(String bucketName, String contentType, String path, String fileName, byte[] imageByte) {
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        try (InputStream in = new ByteArrayInputStream(imageByte)) {
            // 上传图片文件
            MinIoUtil.upload(bucketName, path + fileName, in, contentType);
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InsufficientDataException | InternalException | NoResponseException | InvalidBucketNameException | XmlPullParserException | ErrorResponseException | RegionConflictException | InvalidArgumentException | InvalidPortException | InvalidEndpointException e) {
            throw ServiceException.serviceException("图片上传失败", e).build();
        }
        return "http://" + endpoint + ":" + port + "/" + bucketName + "/" + path + fileName;
    }

    @Override
    public void removeFile(String bucketName, String path) {
        try {
            MinIoUtil.removeFile(bucketName, path);
        } catch (InvalidPortException | InvalidEndpointException | IOException | InvalidKeyException | NoSuchAlgorithmException | InsufficientDataException | InternalException | NoResponseException | InvalidBucketNameException | XmlPullParserException | ErrorResponseException e) {
            throw ServiceException.serviceException("文件删除失败", e).build();
        }
    }
}
