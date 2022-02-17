package com.education.service.edu.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;

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
public class EduVideoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 视频 ID
     */
    private String id;

    /**
     * 课程 ID
     */
    private String courseId;

    /**
     * 章节 ID
     */
    private String chapterId;

    /**
     * 节点名称
     */
    private String title;

    /**
     * 云端视频资源
     */
    private String videoSourceId;

    /**
     * 原始文件名称
     */
    private String videoOriginalName;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 播放次数
     */
    private Long playCount;

    /**
     * 是否可以试听：0 收费 1 免费
     */
    private Boolean isFree;

    /**
     * 视频时长（秒）
     */
    private Float duration;

    /**
     * Empty 未上传 Transcoding 转码中  Normal 正常
     */
    private String status;

    /**
     * 视频源文件大小（字节）
     */
    private Long size;

    /**
     * 乐观锁
     */
    @Version
    private Long version;

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
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public EduVideoDO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public EduVideoDO setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
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
            ", gmtCreate=" + createTime +
            ", gmtModified=" + updateTime +
        "}";
    }
}
