package com.education.service.edu.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.education.service.base.entity.ObjectConvert;
import com.education.service.edu.domain.vo.EduTeacherVO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@TableName("edu_teacher")
public class EduTeacherDO extends ObjectConvert<EduTeacherVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 讲师 ID
     */
    private String id;

    /**
     * 讲师姓名
     */
    private String name;

    /**
     * 讲师简介
     */
    private String intro;

    /**
     * 讲师资历,一句话说明讲师
     */
    private String career;

    /**
     * 头衔 0 普通教师 1 高级讲师 2 首席讲师
     */
    private Integer level;

    /**
     * 讲师头像
     */
    private String avatar;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableLogic
    private Boolean isDeleted;

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

    public EduTeacherDO setId(String id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }

    public EduTeacherDO setName(String name) {
        this.name = name;
        return this;
    }
    public String getIntro() {
        return intro;
    }

    public EduTeacherDO setIntro(String intro) {
        this.intro = intro;
        return this;
    }
    public String getCareer() {
        return career;
    }

    public EduTeacherDO setCareer(String career) {
        this.career = career;
        return this;
    }
    public Integer getLevel() {
        return level;
    }

    public EduTeacherDO setLevel(Integer level) {
        this.level = level;
        return this;
    }
    public String getAvatar() {
        return avatar;
    }

    public EduTeacherDO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
    public Integer getSort() {
        return sort;
    }

    public EduTeacherDO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }
    public Boolean getDeleted() {
        return isDeleted;
    }

    public EduTeacherDO setDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public EduTeacherDO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public EduTeacherDO setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "EduTeacherDO{" +
            "id=" + id +
            ", name=" + name +
            ", intro=" + intro +
            ", career=" + career +
            ", level=" + level +
            ", avatar=" + avatar +
            ", sort=" + sort +
            ", isDeleted=" + isDeleted +
            ", gmtCreate=" + createTime +
            ", gmtModified=" + updateTime +
        "}";
    }
}
