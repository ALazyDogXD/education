package com.education.service.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.education.service.edu.domain.entity.EduSubjectDO;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
public interface EduSubjectMapper extends BaseMapper<EduSubjectDO> {

    /**
     * 获取最大的分类序号
     * @return 最大的分类序号
     */
    int getMaxSort();

}
