package com.knife.serviceedu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.knife.servicebase.entity.ObjectConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@TableName("edu_teacher")
@ApiModel(value="EduTeacherDO对象", description="讲师")
public class EduTeacherDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讲师ID")
    private String id;

    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "讲师简介")
    private String intro;

    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String career;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "讲师头像")
    private String avatar;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;

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
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public EduTeacherDO setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public EduTeacherDO setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
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
            ", gmtCreate=" + gmtCreate +
            ", gmtModified=" + gmtModified +
        "}";
    }
}
