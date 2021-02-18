package com.knife.serviceedu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.knife.serviceedu.domain.dto.EduCourseDTO;
import com.knife.serviceedu.domain.entity.EduCourseDO;
import com.knife.serviceedu.domain.vo.EduCourseVO;
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
     * 获取分页数据
     *
     * @param page  当前页
     * @param size  每页数量
     * @param order 排序字段
     * @return 分页数据
     */
    IPage<EduCourseVO> getList(int page, int size, String order);

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
