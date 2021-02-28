package com.education.service.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.education.service.edu.domain.entity.EduCourseDescriptionDO;
import com.education.service.edu.mapper.EduCourseDescriptionMapper;
import com.education.service.edu.service.EduCourseDescriptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescriptionDO> implements EduCourseDescriptionService {

    @Override
    public void add(String courseId, String desc) {
        save(new EduCourseDescriptionDO() {{
            setId(courseId);
            setDescription(desc);
            setGmtCreate(LocalDateTime.now());
            setGmtModified(LocalDateTime.now());
        }});
    }

}
