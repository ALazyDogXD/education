package com.education.service.edu.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@TableName("edu_comment")
public class EduCommentDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 讲师 ID
     */
    private String id;

    /**
     * 课程 ID
     */
    private String courseId;

    /**
     * 讲师 ID
     */
    private String teacherId;

    /**
     * 会员 ID
     */
    private String memberId;

    /**
     * 会员昵称
     */
    private String nickname;

    /**
     * 会员头像
     */
    private String avatar;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableLogic
    private Boolean isDeleted;

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
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public EduCommentDO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public EduCommentDO setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
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
            ", gmtCreate=" + createTime +
            ", gmtModified=" + updateTime +
        "}";
    }
}
