package com.knife.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knife.serviceedu.domain.entity.EduVideoDO;
import com.knife.serviceedu.mapper.EduVideoMapper;
import com.knife.serviceedu.service.EduVideoService;
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
