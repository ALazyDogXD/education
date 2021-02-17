package com.knife.serviceedu.domain.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr_W
 * @date 2021/2/16 18:56
 * @description: 父节点类目 vo
 */
public class EduSubjectParentVO {

    private String id;

    private Integer sort;

    private String title;

    private List<EduSubjectVO> subjects = new ArrayList<>();

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

    public List<EduSubjectVO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<EduSubjectVO> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "EduSubjectParentVO{" +
                "id='" + id + '\'' +
                ", sort=" + sort +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
