package com.knife.serviceedu.domain.dto;

import com.knife.servicebase.entity.ObjectConvert;
import com.knife.serviceedu.domain.entity.EduTeacherDO;

/**
 * @PackageName：com.knife.serviceedu.domain.dto
 * @ClassName：EduTeahcerDto
 * @Description：//TODO
 * @author：风烛
 * @date：2021-02-17 11:04
 */
public class EduTeacherDto  extends ObjectConvert<EduTeacherDO> {
    private String name;
    private String intro;
    private String career;
    private Integer level;
    private String avatar;


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
