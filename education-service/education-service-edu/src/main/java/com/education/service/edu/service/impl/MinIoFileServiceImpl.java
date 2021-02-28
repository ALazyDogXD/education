package com.education.service.edu.service.impl;

import com.education.common.util.exception.minio.EmptyImageException;
import com.education.common.util.exception.minio.FileTypeException;
import com.education.common.util.exception.minio.ImageSizeOutOfRangeException;
import com.education.common.util.MinIoUtil;
import com.education.service.base.entity.ServiceException;
import com.education.service.edu.service.MinIoFileService;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Mr_W
 * @date 2021/2/19 17:25
 * @description MinIO 文件服务
 */
@Service
public class MinIoFileServiceImpl implements MinIoFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinIoFileServiceImpl.class);

    @Override
    public void uploadThumbnail(String bucketName, MultipartFile image, String path, String fileName) {
        try (InputStream in = image.getInputStream()) {
            String contentType = getImageContentType(image);
            // 上传图片文件
            MinIoUtil.upload(bucketName, path + fileName, in, contentType);
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InsufficientDataException | InternalException | NoResponseException | InvalidBucketNameException | XmlPullParserException | ErrorResponseException | RegionConflictException | InvalidArgumentException | InvalidPortException | InvalidEndpointException e) {
            throw ServiceException.serviceException("图片上传失败", e).build();
        }
    }

    /**
     * 获取图片文件媒体格式
     *
     * @param cover 图片文件
     * @return 媒体格式
     */
    private String getImageContentType(MultipartFile cover) {
        try {
            return MinIoUtil.getThumbnailContentType(cover);
        } catch (IOException e) {
            throw ServiceException.serviceException("文件读写异常", e).alertMessage("不支持的文件格式").build();
        } catch (EmptyImageException e) {
            throw ServiceException.serviceException("图片为空", e).alertMessage("请选择要上传的图片").build();
        } catch (ImageSizeOutOfRangeException e) {
            throw ServiceException.serviceException("图片大小超范围", e).alertMessage("图片文件不得大于 2 MB").build();
        } catch (FileTypeException e) {
            throw ServiceException.serviceException("文件类型错误", e).alertMessage("文件类型错误").build();
        }
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
