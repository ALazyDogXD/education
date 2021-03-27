package com.education.service.edu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程视频
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@TableName("edu_video")
@ApiModel(value="EduVideoDO对象", description="课程视频")
public class EduVideoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "视频ID")
    private String id;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节ID")
    private String chapterId;

    @ApiModelProperty(value = "节点名称")
    private String title;

    @ApiModelProperty(value = "云端视频资源")
    private String videoSourceId;

    @ApiModelProperty(value = "原始文件名称")
    private String videoOriginalName;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty(value = "播放次数")
    private Long playCount;

    @ApiModelProperty(value = "是否可以试听：0收费 1免费")
    private Boolean isFree;

    @ApiModelProperty(value = "视频时长（秒）")
    private Float duration;

    @ApiModelProperty(value = "Empty未上传 Transcoding转码中  Normal正常")
    private String status;

    @ApiModelProperty(value = "视频源文件大小（字节）")
    private Long size;

    @Version
    @ApiModelProperty(value = "乐观锁")
    private Long version;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;

    public String getId() {
        return id;
    }

    public EduVideoDO setId(String id) {
        this.id = id;
        return this;
    }
    public String getCourseId() {
        return courseId;
    }

    public EduVideoDO setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }
    public String getChapterId() {
        return chapterId;
    }

    public EduVideoDO setChapterId(String chapterId) {
        this.chapterId = chapterId;
        return this;
    }
    public String getTitle() {
        return title;
    }

    public EduVideoDO setTitle(String title) {
        this.title = title;
        return this;
    }
    public String getVideoSourceId() {
        return videoSourceId;
    }

    public EduVideoDO setVideoSourceId(String videoSourceId) {
        this.videoSourceId = videoSourceId;
        return this;
    }
    public String getVideoOriginalName() {
        return videoOriginalName;
    }

    public EduVideoDO setVideoOriginalName(String videoOriginalName) {
        this.videoOriginalName = videoOriginalName;
        return this;
    }
    public Integer getSort() {
        return sort;
    }

    public EduVideoDO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }
    public Long getPlayCount() {
        return playCount;
    }

    public EduVideoDO setPlayCount(Long playCount) {
        this.playCount = playCount;
        return this;
    }
    public Boolean getFree() {
        return isFree;
    }

    public EduVideoDO setFree(Boolean isFree) {
        this.isFree = isFree;
        return this;
    }
    public Float getDuration() {
        return duration;
    }

    public EduVideoDO setDuration(Float duration) {
        this.duration = duration;
        return this;
    }
    public String getStatus() {
        return status;
    }

    public EduVideoDO setStatus(String status) {
        this.status = status;
        return this;
    }
    public Long getSize() {
        return size;
    }

    public EduVideoDO setSize(Long size) {
        this.size = size;
        return this;
    }
    public Long getVersion() {
        return version;
    }

    public EduVideoDO setVersion(Long version) {
        this.version = version;
        return this;
    }
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public EduVideoDO setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public EduVideoDO setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    @Override
    public String toString() {
        return "EduVideoDO{" +
            "id=" + id +
            ", courseId=" + courseId +
            ", chapterId=" + chapterId +
            ", title=" + title +
            ", videoSourceId=" + videoSourceId +
            ", videoOriginalName=" + videoOriginalName +
            ", sort=" + sort +
            ", playCount=" + playCount +
            ", isFree=" + isFree +
            ", duration=" + duration +
            ", status=" + status +
            ", size=" + size +
            ", version=" + version +
            ", gmtCreate=" + gmtCreate +
            ", gmtModified=" + gmtModified +
        "}";
    }
}
