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
    private String chapterId;

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
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

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
    public String getChapterId() {
        return chapterId;
    }

    public EduCommentDO setChapterId(String chapterId) {
        this.chapterId = chapterId;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "EduCommentDO{" +
                "id='" + id + '\'' +
                ", courseId='" + courseId + '\'' +
                ", chapterId='" + chapterId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", content='" + content + '\'' +
                ", isDeleted=" + isDeleted +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
