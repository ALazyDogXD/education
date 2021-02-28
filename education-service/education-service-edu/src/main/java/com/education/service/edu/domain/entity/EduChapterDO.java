package com.education.service.edu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@TableName("edu_chapter")
@ApiModel(value="EduChapterDO对象", description="课程")
public class EduChapterDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "章节ID")
    private String id;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
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
