package com.education.service.edu.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.education.service.base.entity.ObjectConvert;
import com.education.service.edu.domain.vo.EduSubjectVO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@TableName("edu_subject")
public class EduSubjectDO extends ObjectConvert<EduSubjectVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程类别 ID
     */
    private String id;

    /**
     * 类别名称
     */
    private String title;

    /**
     * 父 ID
     */
    private String parentId;

    /**
     * 排序字段
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public EduSubjectDO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public EduSubjectDO setUpdateTime(LocalDateTime updateTime) {
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
        return "EduSubjectDO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", parentId='" + parentId + '\'' +
                ", sort=" + sort +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
