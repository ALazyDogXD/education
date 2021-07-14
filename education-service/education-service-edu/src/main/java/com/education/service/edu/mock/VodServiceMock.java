package com.education.service.edu.mock;

import com.education.rpc.vod.service.VodService;
import com.education.service.base.entity.ServiceException;

/**
 * @author Mr_W
 * @date 2021/4/5 16:47
 * @description 点播服务降级
 */
public class VodServiceMock implements VodService {
    @Override
    public void uploadVideo(String bucketName, String path, String fileName, byte[] videoByte) {
        throw ServiceException.serviceException("视频上传失败").build();
    }
}
