package com.education.service.edu.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@TableName("edu_chapter")
public class EduChapterDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 章节 ID
     */
    private String id;

    /**
     * 课程 ID
     */
    private String courseId;

    /**
     * 章节名称
     */
    private String title;

    /**
     * 显示排序
     */
    private Integer sort;

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

    public EduChapterDO setId(String id) {
        this.id = id;
        return this;
    }
    public String getCourseId() {
        return courseId;
    }

    public EduChapterDO setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }
    public String getTitle() {
        return title;
    }

    public EduChapterDO setTitle(String title) {
        this.title = title;
        return this;
    }
    public Integer getSort() {
        return sort;
    }

    public EduChapterDO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public EduChapterDO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public EduChapterDO setUpdateTime(LocalDateTime updateTime) {
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
        return "EduChapterDO{" +
                "id='" + id + '\'' +
                ", courseId='" + courseId + '\'' +
                ", title='" + title + '\'' +
                ", sort=" + sort +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
