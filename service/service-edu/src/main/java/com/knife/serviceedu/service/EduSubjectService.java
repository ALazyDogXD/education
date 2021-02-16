package com.knife.serviceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knife.serviceedu.domain.entity.EduSubjectDO;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
public interface EduSubjectService extends IService<EduSubjectDO> {

    /**
     * 科目类别导入
     * @param file excel 文件
     */
    void importSubjectFile(MultipartFile file);

    /**
     * 通过 title 获取 EduSubjectDO
     * @param title 科目名字
     * @return EduSubjectDO
     */
    EduSubjectDO getByTitle(String title);

    /**
     * 获取最大的分类序号
     * @return 最大的分类序号
     */
    int getMaxSort();

}
