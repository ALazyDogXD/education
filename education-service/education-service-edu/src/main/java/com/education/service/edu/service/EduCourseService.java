package com.education.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.education.service.edu.domain.dto.EduCourseDTO;
import com.education.service.edu.domain.entity.EduCourseDO;
import com.education.service.edu.domain.vo.EduCourseVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    void add(MultipartFile cover, EduCourseDTO course);

    /**
     *
     * 获取分页数据
     *
     * @param page 分页对象
     * @param title 课程名称
     * @param teacherId 教师 id
     * @param subjectParentId 一级学科 id
     * @param subjectId 二级学科 id
     * @return 分页数据
     */
    IPage<EduCourseVO> getList(IPage<EduCourseDO> page, String title, String teacherId, String subjectParentId, String subjectId);

    /**
     * 更新课程
     *
     * @param course 课程入参
     */
    void update(EduCourseDTO course);

    /**
     * 修改课程状态
     *
     * @param ids    课程 id 集合
     * @param status 课程状态
     */
    void updateStatus(List<String> ids, Boolean status);

    /**
     * 删除课程
     *
     * @param ids 课程 id 集合
     */
    void remove(List<String> ids);

}
