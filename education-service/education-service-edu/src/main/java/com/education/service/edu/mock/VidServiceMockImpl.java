package com.education.service.edu.mock;

import com.education.rpc.vid.service.VidService;
import com.education.service.base.entity.ServiceException;

import static com.education.service.base.entity.enums.ResponseEnum.VIDEO_UPLOAD_FAIL;

/**
 * @author Mr_W
 * @date 2021/4/5 16:47
 * @description 点播服务降级
 */
public class VidServiceMockImpl implements VidService {
    @Override
    public void uploadVideo(String bucketName, String path, String fileName, byte[] videoByte) {
        throw new ServiceException(VIDEO_UPLOAD_FAIL);
    }
}
