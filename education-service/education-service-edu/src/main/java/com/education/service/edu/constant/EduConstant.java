package com.education.service.edu.constant;

/**
 * @author Mr_W
 * @date 2021/2/17 21:45
 * @description: edu 常量
 */
public interface EduConstant {

    /**
     * 课程状态: 未发布
     */
    String COURSE_DRAFT = "Draft";

    /**
     * 课程状态: 已发布
     */
    String COURSE_NORMAL = "Normal";

    /**
     * 图片文件大小
     */
    long M2_TO_BYTE = (1 << 20) * 2;

}
