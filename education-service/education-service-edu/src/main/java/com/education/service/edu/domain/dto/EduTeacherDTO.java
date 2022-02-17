package com.education.service.edu.domain.dto;

import com.education.service.base.annotation.SetNull;
import com.education.service.base.annotation.validate.FileNotEmpty;
import com.education.service.base.annotation.validate.FileSize;
import com.education.service.base.annotation.validate.IsImage;
import com.education.service.base.entity.ObjectConvert;
import com.education.service.base.strategy.CreateDataTransferObject;
import com.education.service.base.strategy.UpdateDataTransferObject;
import com.education.service.edu.domain.entity.EduTeacherDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

import static com.education.service.edu.constant.EduConstant.M2_TO_BYTE;

/**
 * @description 教师dto
 * @author 风烛
 * @date 2021-02-17 11:04
 */
@ApiModel("教师入参")
public class EduTeacherDTO extends ObjectConvert<EduTeacherDO> {

    @SetNull(group = CreateDataTransferObject.class)
    @NotBlank(message = "教师 ID 不可为空", groups = UpdateDataTransferObject.class)
    @ApiModelProperty("教师 ID")
    private String id;

    @NotBlank(message = "教师姓名不可为空", groups = CreateDataTransferObject.class)
    @Size(max = 30, message = "讲师姓名不可超过 30 字符")
    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "讲师简介")
    @Size(max = 200, message = "讲师简介不可超过 200 字符")
    private String intro;

    @ApiModelProperty(value = "讲师资历, 一句话说明讲师")
    @Size(max = 200, message = "讲师资历不可超过 200 字符")
    private String career;

    @NotNull(message = "教师头衔不可为空", groups = CreateDataTransferObject.class)
    @ApiModelProperty(value = "头衔 0 普通教师 1 高级讲师 2 首席讲师")
    @Min(value = 0, message = "不存在的讲师头衔")
    @Max(value = 2, message = "不存在的讲师头衔")
    private Integer level;

    @ApiModelProperty(value = "讲师头像")
    @IsImage(groups = CreateDataTransferObject.class)
    @FileSize(max = M2_TO_BYTE, message = "图片大小不可超过 2M",
            groups = CreateDataTransferObject.class)
    @FileNotEmpty(groups = CreateDataTransferObject.class)
    private MultipartFile avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "EduTeacherDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", career='" + career + '\'' +
                ", level=" + level +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
