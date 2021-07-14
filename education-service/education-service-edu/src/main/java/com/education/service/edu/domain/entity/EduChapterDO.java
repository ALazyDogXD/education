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
     * 章节ID
     */
    private String id;

    /**
     * 课程ID
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
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public EduChapterDO setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public EduChapterDO setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    @Override
    public String toString() {
        return "EduChapterDO{" +
            "id=" + id +
            ", courseId=" + courseId +
            ", title=" + title +
            ", sort=" + sort +
            ", gmtCreate=" + gmtCreate +
            ", gmtModified=" + gmtModified +
        "}";
    }
}
