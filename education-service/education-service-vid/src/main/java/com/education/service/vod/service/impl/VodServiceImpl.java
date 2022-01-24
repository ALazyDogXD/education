package com.education.service.vod.service.impl;

import com.education.rpc.minio.service.MinIoFileService;
import com.education.rpc.vod.service.VodService;
import com.education.service.base.entity.ServiceException;
import com.education.service.vod.util.FfmpegUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

import static com.education.service.base.entity.enums.ResponseEnum.FILE_DEL_FAIL;
import static com.education.service.base.entity.enums.ResponseEnum.VIDEO_UPLOAD_FAIL;

/**
 * @author Mr_W
 * @date 2021/4/4 15:23
 * @description 视频服务
 */
@DubboService
public class VodServiceImpl implements VodService, CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(VodServiceImpl.class);

    @Value("${vod.temp-file-path}")
    private String path;

    @DubboReference
    private MinIoFileService minIoFileService;

    @Override
    public void run(String... args) throws Exception {
        try {
            path = path.endsWith(File.separator) ? path.substring(0, path.lastIndexOf(File.separator)) : path;
            if (Files.exists(Paths.get(path))) {
                LOGGER.info("开始清空临时视频文件");
                Instant start = Instant.now();
                deleteFile(Paths.get(path));
                LOGGER.info("临时视频文件清空完毕 [{} ms]", Duration.between(start, Instant.now()).toMillis());
            }
        } catch (IOException e) {
            LOGGER.error("临时文件夹清理失败", e);
            throw e;
        }
        try {
            Files.createDirectories(Paths.get(path));
            LOGGER.info("临时文件夹创建成功");
        } catch (IOException e) {
            LOGGER.error("临时文件夹创建失败", e);
            throw e;
        }
    }

    private void deleteFile(Path rootPath) throws IOException {
        Path[] paths = Files.list(rootPath).toArray(Path[]::new);
        for (Path path : paths) {
            if (Files.isDirectory(path)) {
                deleteFile(path);
            } else {
                Files.delete(path);
            }
        }
    }

    @Override
    public void uploadVideo(String bucketName, String path, String fileName, byte[] videoByte) {
        String filePath = (this.path.endsWith(File.separator) ? this.path : (this.path + File.separator)) + fileName;
        if (Files.exists(Paths.get(filePath))) {
            throw new ServiceException(VIDEO_UPLOAD_FAIL);
        }

        // 视频切片
        String mpdPath;
        try {
            Files.write(Paths.get(filePath), videoByte);
            mpdPath = FfmpegUtil.convertVideoIntoMpd(filePath);
            Files.delete(Paths.get(filePath));
        } catch (IOException e) {
            throw new ServiceException(VIDEO_UPLOAD_FAIL, e);
        }

        // 上传视频
        try {
            minIoFileService.upload(bucketName, path, fileName, Files.readAllBytes(Paths.get(mpdPath)));
            Files.delete(Paths.get(mpdPath));
            uploadAndDeleteM4s(bucketName, path, fileName);
        } catch (IOException e) {
            throw new ServiceException(VIDEO_UPLOAD_FAIL, e);
        }
    }

    /**
     * 上传并且删除临时文件的 init-xxx.m4s
     *
     * @param bucketName 桶名
     * @param path       文件服务器路径
     * @param fileName   分片视频文件名
     * @throws IOException 读写异常
     */
    private void uploadAndDeleteM4s(String bucketName, String path, String fileName) throws IOException {
        String videoName;
        Path filePath;
        for (int i = 0; Files.exists(filePath = Paths.get((this.path.endsWith(File.separator) ? this.path : (this.path + File.separator)) + (videoName = "init-" + fileName.substring(0, fileName.indexOf(".") - 1) + i + ".m4s"))); i++) {
            minIoFileService.upload(bucketName, path, videoName, Files.readAllBytes(filePath));
            deleteTempVideo(filePath);
            for (int j = 0; Files.exists(filePath = Paths.get((this.path.endsWith(File.separator) ? this.path : (this.path + File.separator)) + (videoName = "chunk-" + fileName.substring(0, fileName.indexOf(".") - 1) + i + "-" + String.format("%04d", j) + ".m4s"))); j++) {
                minIoFileService.upload(bucketName, path, videoName, Files.readAllBytes(filePath));
                deleteTempVideo(filePath);
            }
        }
    }

    /**
     * 删除临时视频文件
     *
     * @param filePath 文件路径
     */
    private void deleteTempVideo(Path filePath) {
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new ServiceException(FILE_DEL_FAIL, e);
        }
    }

}
