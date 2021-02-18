package com.knife.serviceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knife.serviceedu.domain.entity.EduChapterDO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
public interface EduChapterService extends IService<EduChapterDO> {

    /**
     * 获取章节集合
     *
     * @param courseId 课程 id 集合
     * @return 章节集合
     */
    List<EduChapterDO> getByChapterId(String courseId);

}
