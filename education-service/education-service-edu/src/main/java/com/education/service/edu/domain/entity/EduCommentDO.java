package com.education.service.edu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@TableName("edu_comment")
@ApiModel(value="EduCommentDO对象", description="评论")
public class EduCommentDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讲师ID")
    private String id;

    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "会员id")
    private String memberId;

    @ApiModelProperty(value = "会员昵称")
    private String nickname;

    @ApiModelProperty(value = "会员头像")
    private String avatar;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @TableLogic
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;

    public String getId() {
        return id;
    }

    public EduCommentDO setId(String id) {
        this.id = id;
        return this;
    }
    public String getCourseId() {
        return courseId;
    }

    public EduCommentDO setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }
    public String getTeacherId() {
        return teacherId;
    }

    public EduCommentDO setTeacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }
    public String getMemberId() {
        return memberId;
    }

    public EduCommentDO setMemberId(String memberId) {
        this.memberId = memberId;
        return this;
    }
    public String getNickname() {
        return nickname;
    }

    public EduCommentDO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    public String getAvatar() {
        return avatar;
    }

    public EduCommentDO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
    public String getContent() {
        return content;
    }

    public EduCommentDO setContent(String content) {
        this.content = content;
        return this;
    }
    public Boolean getDeleted() {
        return isDeleted;
    }

    public EduCommentDO setDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public EduCommentDO setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public EduCommentDO setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    @Override
    public String toString() {
        return "EduCommentDO{" +
            "id=" + id +
            ", courseId=" + courseId +
            ", teacherId=" + teacherId +
            ", memberId=" + memberId +
            ", nickname=" + nickname +
            ", avatar=" + avatar +
            ", content=" + content +
            ", isDeleted=" + isDeleted +
            ", gmtCreate=" + gmtCreate +
            ", gmtModified=" + gmtModified +
        "}";
    }
}
