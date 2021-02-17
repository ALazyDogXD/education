package com.knife.serviceedu.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mr_W
 * @date 2021/2/16 18:56
 * @description: 父节点类目 vo
 */
@ApiModel(value = "一级科目")
public class EduSubjectParentVO extends EduSubjectVO {

    @ApiModelProperty(value = "二级科目")
    private List<EduSubjectVO> subjects = new ArrayList<>();

    public List<EduSubjectVO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<EduSubjectVO> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "EduSubjectParentVO{" +
                "id='" + getId() + '\'' +
                ", sort=" + getSort() +
                ", title='" + getTitle() + '\'' +
                ", subjects=" + Arrays.toString(subjects.toArray()) +
                '}';
    }
}
