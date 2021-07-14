package com.education.service.edu.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程简介
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@TableName("edu_course_description")
public class EduCourseDescriptionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    private String id;

    /**
     * 课程简介
     */
    private String description;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    public String getId() {
        return id;
    }

    public EduCourseDescriptionDO setId(String id) {
        this.id = id;
        return this;
    }
    public String getDescription() {
        return description;
    }

    public EduCourseDescriptionDO setDescription(String description) {
        this.description = description;
        return this;
    }
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public EduCourseDescriptionDO setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public EduCourseDescriptionDO setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    @Override
    public String toString() {
        return "EduCourseDescriptionDO{" +
            "id=" + id +
            ", description=" + description +
            ", gmtCreate=" + gmtCreate +
            ", gmtModified=" + gmtModified +
        "}";
    }
}
