package com.knife.serviceedu.domain.dto;

import com.knife.servicebase.entity.ObjectConvert;
import com.knife.serviceedu.domain.entity.EduTeacherDO;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @PackageName：com.knife.serviceedu.domain.dto
 * @ClassName：EduTeahcerDto
 * @Description：//TODO
 * @author：风烛
 * @date：2021-02-17 11:04
 */
public class EduTeacherDto  extends ObjectConvert<EduTeacherDO> {
    private String id;
    @NotBlank(message = "教师姓名不可为空")
    private String name;
    private String intro;
    private String career;
    private Integer level;
    private String avatar;
    private LocalDateTime gmtCreate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    private LocalDateTime gmtModified;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
