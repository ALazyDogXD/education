package com.education.service.edu.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Mr_W
 * @date 2021/2/16 19:04
 * @description 子节点类目 vo
 */
@ApiModel(value = "二级科目")
public class EduSubjectVO {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "顺序")
    private Integer sort;

    @ApiModelProperty(value = "科目标题")
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "EduSubjectVO{" +
                "id='" + id + '\'' +
                ", sort=" + sort +
                ", title='" + title + '\'' +
                '}';
    }
}
