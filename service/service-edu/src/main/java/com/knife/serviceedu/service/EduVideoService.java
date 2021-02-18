package com.knife.serviceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knife.serviceedu.domain.entity.EduVideoDO;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
public interface EduVideoService extends IService<EduVideoDO> {

    /**
     * 获取视频集合
     *
     * @param courseId 课程 id 集合
     * @return 视频集合
     */
    List<EduVideoDO> getByCourseId(String courseId);

    /**
     * 获取视频集合
     *
     * @param chapterId 章节 id 集合
     * @return 视频集合
     */
    List<EduVideoDO> getByChapterId(String chapterId);

}
