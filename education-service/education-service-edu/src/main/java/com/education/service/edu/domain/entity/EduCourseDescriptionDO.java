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
     * 课程 ID
     */
    private String id;

    /**
     * 课程简介
     */
    private String description;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

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
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public EduCourseDescriptionDO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public EduCourseDescriptionDO setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "EduCourseDescriptionDO{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
