package com.education.service.edu.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程收藏
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@TableName("edu_course_collect")
public class EduCourseCollectDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收藏ID
     */
    private String id;

    /**
     * 课程讲师ID
     */
    private String courseId;

    /**
     * 课程专业ID
     */
    private String memberId;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableLogic
    private Integer isDeleted;

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

    public EduCourseCollectDO setId(String id) {
        this.id = id;
        return this;
    }
    public String getCourseId() {
        return courseId;
    }

    public EduCourseCollectDO setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }
    public String getMemberId() {
        return memberId;
    }

    public EduCourseCollectDO setMemberId(String memberId) {
        this.memberId = memberId;
        return this;
    }
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public EduCourseCollectDO setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public EduCourseCollectDO setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public EduCourseCollectDO setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    @Override
    public String toString() {
        return "EduCourseCollectDO{" +
            "id=" + id +
            ", courseId=" + courseId +
            ", memberId=" + memberId +
            ", isDeleted=" + isDeleted +
            ", gmtCreate=" + gmtCreate +
            ", gmtModified=" + gmtModified +
        "}";
    }
}
