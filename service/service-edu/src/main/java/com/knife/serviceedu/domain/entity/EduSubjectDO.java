package com.knife.serviceedu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.knife.servicebase.entity.ObjectConvert;
import com.knife.serviceedu.domain.vo.EduSubjectVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@TableName("edu_subject")
@ApiModel(value = "EduSubjectDO对象", description = "课程科目")
public class EduSubjectDO extends ObjectConvert<EduSubjectVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程类别ID")
    private String id;

    @ApiModelProperty(value = "类别名称")
    private String title;

    @ApiModelProperty(value = "父ID")
    private String parentId;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;

    public String getId() {
        return id;
    }

    public EduSubjectDO setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public EduSubjectDO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getParentId() {
        return parentId;
    }

    public EduSubjectDO setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public EduSubjectDO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public EduSubjectDO setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public EduSubjectDO setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    @Override
    public String toString() {
        return "EduSubjectDO{" +
                "id=" + id +
                ", title=" + title +
                ", parentId=" + parentId +
                ", sort=" + sort +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "}";
    }
}
