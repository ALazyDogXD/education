package com.education.service.edu.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.education.service.base.entity.ObjectConvert;
import com.education.service.edu.domain.vo.EduCourseVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@TableName("edu_course")
public class EduCourseDO extends ObjectConvert<EduCourseVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程 ID
     */
    private String id;

    /**
     * 课程讲师 ID
     */
    private String teacherId;

    /**
     * 课程专业 ID
     */
    private String subjectId;

    /**
     * 课程专业父级 ID
     */
    private String subjectParentId;

    /**
     * 课程标题
     */
    private String title;

    /**
     * 课程销售价格，设置为 0 则可免费观看
     */
    private BigDecimal price;

    /**
     * 总课时
     */
    private Integer lessonNum;

    /**
     * 课程封面图片路径
     */
    private String cover;

    /**
     * 销售数量
     */
    private Long buyCount;

    /**
     * 浏览数量
     */
    private Long viewCount;

    /**
     * 乐观锁
     */
    @Version
    private Long version;

    /**
     * 课程状态 Draft 未发布 Normal 已发布
     */
    private String status;

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

    public String getId() {
        return id;
    }

    public EduCourseDO setId(String id) {
        this.id = id;
        return this;
    }
    public String getTeacherId() {
        return teacherId;
    }

    public EduCourseDO setTeacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }
    public String getSubjectId() {
        return subjectId;
    }

    public EduCourseDO setSubjectId(String subjectId) {
        this.subjectId = subjectId;
        return this;
    }
    public String getSubjectParentId() {
        return subjectParentId;
    }

    public EduCourseDO setSubjectParentId(String subjectParentId) {
        this.subjectParentId = subjectParentId;
        return this;
    }
    public String getTitle() {
        return title;
    }

    public EduCourseDO setTitle(String title) {
        this.title = title;
        return this;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public EduCourseDO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    public Integer getLessonNum() {
        return lessonNum;
    }

    public EduCourseDO setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
        return this;
    }
    public String getCover() {
        return cover;
    }

    public EduCourseDO setCover(String cover) {
        this.cover = cover;
        return this;
    }
    public Long getBuyCount() {
        return buyCount;
    }

    public EduCourseDO setBuyCount(Long buyCount) {
        this.buyCount = buyCount;
        return this;
    }
    public Long getViewCount() {
        return viewCount;
    }

    public EduCourseDO setViewCount(Long viewCount) {
        this.viewCount = viewCount;
        return this;
    }
    public Long getVersion() {
        return version;
    }

    public EduCourseDO setVersion(Long version) {
        this.version = version;
        return this;
    }
    public String getStatus() {
        return status;
    }

    public EduCourseDO setStatus(String status) {
        this.status = status;
        return this;
    }
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public EduCourseDO setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public EduCourseDO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public EduCourseDO setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "EduCourseDO{" +
            "id=" + id +
            ", teacherId=" + teacherId +
            ", subjectId=" + subjectId +
            ", subjectParentId=" + subjectParentId +
            ", title=" + title +
            ", price=" + price +
            ", lessonNum=" + lessonNum +
            ", cover=" + cover +
            ", buyCount=" + buyCount +
            ", viewCount=" + viewCount +
            ", version=" + version +
            ", status=" + status +
            ", isDeleted=" + isDeleted +
            ", gmtCreate=" + createTime +
            ", gmtModified=" + updateTime +
        "}";
    }
}
