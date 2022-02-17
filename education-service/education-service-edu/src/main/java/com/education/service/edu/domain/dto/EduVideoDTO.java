package com.education.service.edu.domain.dto;

import com.education.service.base.annotation.SetNull;
import com.education.service.base.annotation.validate.FileNotEmpty;
import com.education.service.base.entity.ObjectConvert;
import com.education.service.base.strategy.CreateDataTransferObject;
import com.education.service.base.strategy.UpdateDataTransferObject;
import com.education.service.edu.domain.entity.EduVideoDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Mr_W
 * @date 2021/3/27 19:14
 * @description 视频 DTO
 */
@ApiModel(value = "EduVideoDO对象", description = "课程视频")
public class EduVideoDTO extends ObjectConvert<EduVideoDO> {

    @SetNull(group = CreateDataTransferObject.class)
    @NotBlank(message = "视频 ID 不可为空", groups = UpdateDataTransferObject.class)
    @ApiModelProperty(value = "视频 ID")
    private String id;

    @NotBlank(groups = CreateDataTransferObject.class,
            message = "课程 ID 不可为空")
    @ApiModelProperty(value = "课程 ID")
    private String courseId;

    @NotBlank(groups = CreateDataTransferObject.class,
            message = "章节 ID 不可为空")
    @ApiModelProperty(value = "章节 ID")
    private String chapterId;

    @NotBlank(groups = CreateDataTransferObject.class,
            message = "视频标题不可为空")
    @ApiModelProperty(value = "节点名称")
    @Size(max = 40, message = "视频标题不可超过 40 字符")
    private String title;

    @NotNull(groups = CreateDataTransferObject.class,
            message = "请选择要上传的视频文件")
    @FileNotEmpty(groups = CreateDataTransferObject.class)
    @ApiModelProperty(value = "视频")
    private MultipartFile video;

    @NotNull(groups = CreateDataTransferObject.class,
            message = "是否免费不可为空")
    @ApiModelProperty(value = "是否可以试听：0 收费 1 免费")
    private Boolean isFree;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getVideo() {
        return video;
    }

    public void setVideo(MultipartFile video) {
        this.video = video;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    @Override
    public String toString() {
        return "EduVideoDTO{" +
                "id='" + id + '\'' +
                ", courseId='" + courseId + '\'' +
                ", chapterId='" + chapterId + '\'' +
                ", title='" + title + '\'' +
                ", video=" + video +
                ", isFree=" + isFree +
                '}';
    }
}
