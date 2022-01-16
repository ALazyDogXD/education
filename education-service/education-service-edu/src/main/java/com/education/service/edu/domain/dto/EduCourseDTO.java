package com.education.service.edu.domain.dto;

import com.education.service.base.annotation.SetNull;
import com.education.service.base.annotation.validate.FileNotEmpty;
import com.education.service.base.annotation.validate.FileSize;
import com.education.service.base.annotation.validate.IsImage;
import com.education.service.base.entity.ObjectConvert;
import com.education.service.base.strategy.CreateDataTransferObject;
import com.education.service.base.strategy.UpdateDataTransferObject;
import com.education.service.edu.domain.entity.EduCourseDO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static com.education.service.edu.constant.EduConstant.M2_TO_BYTE;

/**
 * @author Mr_W
 * @date 2021/2/17 18:54
 * @description 课程 dto
 */
@ApiModel("课程实体")
public class EduCourseDTO extends ObjectConvert<EduCourseDO> {

    @SetNull(group = CreateDataTransferObject.class)
    @NotBlank(message = "课程 ID 不可为空", groups = UpdateDataTransferObject.class)
    @ApiModelProperty(value = "课程 ID")
    @JsonIgnore
    private String id;

    @NotBlank(message = "课程讲师不可为空", groups = CreateDataTransferObject.class)
    @ApiModelProperty(value = "课程讲师 ID")
    private String teacherId;

    @NotBlank(message = "课程专业不可为空", groups = CreateDataTransferObject.class)
    @ApiModelProperty(value = "课程专业 ID")
    private String subjectId;

    @NotBlank(message = "课程专业父级 ID 不可为空", groups = CreateDataTransferObject.class)
    @ApiModelProperty(value = "课程专业父级 ID")
    private String subjectParentId;

    @NotBlank(message = "课程名称不可为空", groups = CreateDataTransferObject.class)
    @ApiModelProperty("课程名称")
    private String title;

    @ApiModelProperty("课程封面")
    @IsImage(groups = CreateDataTransferObject.class)
    @FileSize(max = M2_TO_BYTE, message = "图片大小不可超过 2M",
            groups = CreateDataTransferObject.class)
    @FileNotEmpty(groups = CreateDataTransferObject.class)
    private MultipartFile cover;

    @NotNull(message = "课程价格不可为空", groups = CreateDataTransferObject.class)
    @Min(value = 0, message = "课程价格不可小于 0", groups = CreateDataTransferObject.class)
    @ApiModelProperty("课程价格")
    private BigDecimal price;

    @NotNull(message = "课程课时不可为空", groups = CreateDataTransferObject.class)
    @Min(value = 0, message = "课程课时不可小于 0", groups = CreateDataTransferObject.class)
    @ApiModelProperty("课程课时")
    private Integer lessonNum;

    @ApiModelProperty("课程简介")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectParentId() {
        return subjectParentId;
    }

    public void setSubjectParentId(String subjectParentId) {
        this.subjectParentId = subjectParentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getCover() {
        return cover;
    }

    public void setCover(MultipartFile cover) {
        this.cover = cover;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "EduCourseDTO{" +
                "teacherId='" + teacherId + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", subjectParentId='" + subjectParentId + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", lessonNum=" + lessonNum +
                ", description='" + description + '\'' +
                '}';
    }
}
