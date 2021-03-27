package com.education.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.education.service.edu.domain.dto.EduTeacherDTO;
import com.education.service.edu.domain.entity.EduTeacherDO;
import com.education.service.edu.domain.vo.EduTeacherVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
public interface EduTeacherService extends IService<EduTeacherDO> {
    /**
     * 新增一名教师
     *
     * @param teacher 教师入参
     * @param file    头像
     */
    void insert(EduTeacherDTO teacher, MultipartFile file);

    /**
     * 根据 id 对教师数据进行修改
     *
     * @param teacher 教师入参
     */
    void update(EduTeacherDTO teacher);

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @param name 教师名称
     * @return 分页查询结果
     */
    IPage<EduTeacherVO> select(IPage<EduTeacherDO> page, String name);


}
