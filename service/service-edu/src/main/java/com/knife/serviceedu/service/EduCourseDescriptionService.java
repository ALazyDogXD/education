package com.knife.serviceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knife.serviceedu.domain.entity.EduCourseDescriptionDO;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescriptionDO> {

    /**
     * 添加课程简介
     *
     * @param courseId 课程 id
     * @param desc     课程介绍
     */
    void add(String courseId, String desc);

}
