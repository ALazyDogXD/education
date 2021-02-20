package com.knife.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knife.serviceedu.domain.entity.EduChapterDO;
import com.knife.serviceedu.mapper.EduChapterMapper;
import com.knife.serviceedu.service.EduChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapterDO> implements EduChapterService {

    private static final Logger LOGGER = getLogger(EduChapterServiceImpl.class);

    @Override
    public List<EduChapterDO> getByChapterId(String courseId) {
        return baseMapper.selectList(new LambdaQueryWrapper<EduChapterDO>().eq(EduChapterDO::getCourseId, courseId));
    }

}
