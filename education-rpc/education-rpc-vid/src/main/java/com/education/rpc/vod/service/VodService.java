package com.education.rpc.vod.service;

/**
 * @author Mr_W
 * @date 2021/4/4 15:26
 * @description 视频服务
 */
public interface VodService {

    /**
     * 上传视频
     *
     * @param bucketName  桶名
     * @param path        文件路径
     * @param fileName    文件名称
     * @param videoByte   视频字节数组
     */
    void uploadVideo(String bucketName, String path, String fileName, byte[] videoByte);

}
