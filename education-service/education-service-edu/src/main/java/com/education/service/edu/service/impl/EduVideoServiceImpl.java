package com.education.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.education.rpc.vod.service.VodService;
import com.education.service.base.entity.ServiceException;
import com.education.service.edu.domain.entity.EduVideoDO;
import com.education.service.edu.mapper.EduVideoMapper;
import com.education.service.edu.service.EduVideoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.education.service.base.entity.enums.ResponseEnum.VIDEO_IS_NULL;
import static com.education.service.base.entity.enums.ResponseEnum.VIDEO_UPLOAD_FAIL;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@Service
@RefreshScope
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideoDO> implements EduVideoService {

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.path.video.course-video}")
    private String path;

    @DubboReference(mock = "com.education.service.edu.mock.VodServiceMockImpl", timeout = 600000)
    private VodService vodService;

    @Override
    public List<EduVideoDO> getByCourseId(String courseId) {
        return baseMapper.selectList(new LambdaQueryWrapper<EduVideoDO>().eq(EduVideoDO::getCourseId, courseId));
    }

    @Override
    public List<EduVideoDO> getByChapterId(String chapterId) {
        return baseMapper.selectList(new LambdaQueryWrapper<EduVideoDO>().eq(EduVideoDO::getChapterId, chapterId));
    }

    @Override
    public void uploadVideo(MultipartFile video) {
        if (Objects.nonNull(video) && !video.isEmpty()) {
            // 上传视频
            try {
                vodService.uploadVideo(bucketName, path, video.getOriginalFilename(), video.getBytes());
            } catch (IOException e) {
                throw new ServiceException(VIDEO_UPLOAD_FAIL);
            }
        } else {
            throw new ServiceException(VIDEO_IS_NULL);
        }
    }
}
