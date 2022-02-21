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
     * 收藏 ID
     */
    private String id;

    /**
     * 课程讲师 ID
     */
    private String courseId;

    /**
     * 课程专业 ID
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
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public EduCourseCollectDO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public EduCourseCollectDO setUpdateTime(LocalDateTime updateTime) {
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
        return "EduCourseCollectDO{" +
                "id='" + id + '\'' +
                ", courseId='" + courseId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", isDeleted=" + isDeleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
