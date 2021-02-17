package com.knife.serviceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knife.serviceedu.domain.dto.EduCourseDTO;
import com.knife.serviceedu.domain.entity.EduCourseDO;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
public interface EduCourseService extends IService<EduCourseDO> {

    /**
     * 添加课程
     *
     * @param cover  课程封面
     * @param course 课程 dto
     */
    void addCourse(MultipartFile cover, EduCourseDTO course);

}
