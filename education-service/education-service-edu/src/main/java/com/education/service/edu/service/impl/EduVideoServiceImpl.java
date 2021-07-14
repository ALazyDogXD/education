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

    @DubboReference(mock = "com.education.service.edu.mock.VodServiceMock", timeout = 600000)
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
                throw ServiceException.serviceException("视频字节数组获取失败", e).alertMessage("视频上传失败").build();
            }
        } else {
            throw ServiceException.serviceException("请选择上传的视频文件").build();
        }
    }
}
