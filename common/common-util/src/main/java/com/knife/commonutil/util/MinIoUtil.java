package com.knife.commonutil.util;

import com.knife.commonutil.exception.EmptyImageException;
import com.knife.commonutil.exception.FileTypeException;
import com.knife.commonutil.exception.ImageSizeOutOfRangeException;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import javax.imageio.ImageIO;
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

    /**
     * 图片文件大小
     */
    private static final long M2_TO_BYTE = (1 << 20) * 2;

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

    /**
     * 获取图片文件的媒体格式
     *
     * @param image 图片文件
     * @return 媒体格式
     * @throws EmptyImageException          图片为空
     * @throws ImageSizeOutOfRangeException 图片大小超范围
     * @throws FileTypeException            文件类型错误
     */
    public static String getImageContentType(MultipartFile image) throws IOException, EmptyImageException, ImageSizeOutOfRangeException, FileTypeException {
        //检查文件是否为空
        if (Objects.isNull(image) || image.isEmpty()) {
            throw new EmptyImageException("请选择图片");
        }
        //检查文件大小
        if (image.getSize() > M2_TO_BYTE) {
            throw new ImageSizeOutOfRangeException("请上传2M以内的图片");
        }
        //检查是否是图片
        if (Objects.isNull(ImageIO.read(image.getInputStream()))) {
            throw new FileTypeException("上传的文件不是图片");
        }
        String suffix = Objects.requireNonNull(image.getOriginalFilename()).substring(image.getOriginalFilename().lastIndexOf(".") + 1);
        return "image/" + ("jpg".equals(suffix) || "jfif".equals(suffix) ? "jpeg" : suffix);
    }

    /**
     * 删除文件
     *
     * @param bucketName 桶名称
     * @param filePath   文件路径
     */
    public static void removeFile(String bucketName, String filePath) throws InvalidPortException, InvalidEndpointException, IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        MinioClient minioClient = getMinioClient();
        minioClient.removeObject(bucketName, filePath);
    }

}
