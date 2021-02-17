package com.knife.commonutil.util;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author Mr_W
 * @date 2021/2/17 15:05
 * @description: MinIo 工具类
 */
@Component
public class MinIoUtil {

    private static MinioClient MINIO_CLIENT;

    private static String endpoint;

    private static int port;

    private static String accessKey;

    private static String secretKey;

    @Value("${minio.endpoint}")
    public void setEndpoint(String endpoint) {
        MinIoUtil.endpoint = endpoint;
    }

    @Value("${minio.port}")
    public void setPort(int port) {
        MinIoUtil.port = port;
    }

    @Value("${minio.accessKey}")
    public void setAccessKey(String accessKey) {
        MinIoUtil.accessKey = accessKey;
    }

    @Value("${minio.secretKey}")
    public void setSecretKey(String secretKey) {
        MinIoUtil.secretKey = secretKey;
    }

    /**
     * 获取 MinIo 客户端
     *
     * @return MinIo 客户端
     */
    private static MinioClient getMinioClient() throws InvalidPortException, InvalidEndpointException {
        if (Objects.isNull(MINIO_CLIENT)) {
            synchronized (MinIoUtil.class) {
                if (Objects.isNull(MINIO_CLIENT)) {
                    MINIO_CLIENT = new MinioClient(endpoint, port, accessKey, secretKey);
                }
            }
        }
        return MINIO_CLIENT;
    }

    /**
     * 上传
     *
     * @param bucketName 桶名称
     * @param fileName   文件名称(含路径)
     * @param in         文件流
     * @throws IOException                连接异常
     * @throws InternalException          内部异常
     * @throws NoResponseException        服务器无响应
     * @throws InvalidBucketNameException 非法的存储桶名称
     * @throws XmlPullParserException     解析返回的XML异常
     * @throws ErrorResponseException     执行失败
     */
    public static void upload(String bucketName, String fileName, InputStream in) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, RegionConflictException, InvalidArgumentException, InvalidPortException, InvalidEndpointException {
        MinioClient minioClient = getMinioClient();
        if (!minioClient.bucketExists(bucketName)) {
            minioClient.makeBucket(bucketName);
        }
        minioClient.putObject(bucketName, fileName, in, in.available(), "application/octet-stream");
    }

    /**
     * 上传
     *
     * @param bucketName  桶名称
     * @param fileName    文件名称(含路径)
     * @param in          文件流
     * @param contentType 媒体格式
     */
    public static void upload(String bucketName, String fileName, InputStream in, String contentType) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, RegionConflictException, InvalidArgumentException, InvalidPortException, InvalidEndpointException {
        MinioClient minioClient = getMinioClient();
        if (!minioClient.bucketExists(bucketName)) {
            minioClient.makeBucket(bucketName);
        }
        minioClient.putObject(bucketName, fileName, in, in.available(), contentType);
    }

    /**
     * 下载
     *
     * @param bucketName 桶名称
     * @param fileName   文件名称(含路径)
     * @return 文件流
     */
    public static InputStream download(String bucketName, String fileName) throws InvalidPortException, InvalidEndpointException, IOException, XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException, NoResponseException, InvalidBucketNameException, InsufficientDataException, InternalException, InvalidArgumentException {
        MinioClient minioClient = getMinioClient();
        if (fileIsExists(bucketName, fileName)) {
            return minioClient.getObject(bucketName, fileName);
        }
        // 没有找到文件
        return null;
    }

    /**
     * 文件是否存在
     *
     * @param bucketName 桶名称
     * @param fileName   文件名称(含路径)
     * @return true 存在
     */
    public static boolean fileIsExists(String bucketName, String fileName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, InvalidPortException, InvalidEndpointException {
        MinioClient minioClient = getMinioClient();
        if (minioClient.bucketExists(bucketName)) {
            for (Result<Item> result : minioClient.listObjects(bucketName)) {
                if (result.get().name.equals(fileName)) {
                    return true;
                }
            }

        }
        return false;
    }

}
