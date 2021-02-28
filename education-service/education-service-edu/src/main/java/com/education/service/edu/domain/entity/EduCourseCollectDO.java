package com.education.service.edu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 课程收藏
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@TableName("edu_course_collect")
@ApiModel(value="EduCourseCollectDO对象", description="课程收藏")
public class EduCourseCollectDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "收藏ID")
    private String id;

    @ApiModelProperty(value = "课程讲师ID")
    private String courseId;

    @ApiModelProperty(value = "课程专业ID")
    private String memberId;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
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
