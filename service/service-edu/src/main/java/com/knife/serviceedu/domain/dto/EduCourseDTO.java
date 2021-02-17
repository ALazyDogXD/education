package com.knife.serviceedu.domain.dto;

import com.knife.servicebase.entity.ObjectConvert;
import com.knife.serviceedu.domain.entity.EduCourseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Mr_W
 * @date 2021/2/17 18:54
 * @description: 课程 dto
 */
@ApiModel("课程实体")
public class EduCourseDTO extends ObjectConvert<EduCourseDO> {

    @NotBlank(message = "课程讲师ID不可为空")
    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    @NotBlank(message = "课程专业ID不可为空")
    @ApiModelProperty(value = "课程专业ID")
    private String subjectId;

    @NotBlank(message = "课程专业父级ID不可为空")
    @ApiModelProperty(value = "课程专业父级ID")
    private String subjectParentId;

    @NotBlank(message = "课程名称不可为空")
    @ApiModelProperty("课程名称")
    private String title;

    @NotNull(message = "课程封面不可为空")
    @ApiModelProperty("课程封面")
    private MultipartFile cover;

    @NotNull(message = "课程价格不可为空")
    @Min(value = 0, message = "课程价格不可小于 0")
    @ApiModelProperty("课程价格")
    private BigDecimal price;

    @NotNull(message = "课程课时不可为空")
    @Min(value = 0, message = "课程课时不可小于 0")
    @ApiModelProperty("课程课时")
    private Integer lessonNum;

    @NotBlank(message = "课程简介不可为空")
    @ApiModelProperty("课程简介")
    private String description;

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
