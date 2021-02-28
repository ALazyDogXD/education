package com.education.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.education.service.edu.domain.entity.EduVideoDO;
import com.education.service.edu.mapper.EduVideoMapper;
import com.education.service.edu.service.EduVideoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideoDO> implements EduVideoService {

    @Override
    public List<EduVideoDO> getByCourseId(String courseId) {
        return baseMapper.selectList(new LambdaQueryWrapper<EduVideoDO>().eq(EduVideoDO::getCourseId, courseId));
    }

    @Override
    public List<EduVideoDO> getByChapterId(String chapterId) {
        return baseMapper.selectList(new LambdaQueryWrapper<EduVideoDO>().eq(EduVideoDO::getChapterId, chapterId));
    }
}
