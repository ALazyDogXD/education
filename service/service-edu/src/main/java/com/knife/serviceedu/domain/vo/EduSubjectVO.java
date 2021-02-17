package com.knife.serviceedu.domain.vo;

/**
 * @author Mr_W
 * @date 2021/2/16 19:04
 * @description: 子节点类目 vo
 */
public class EduSubjectVO {

    private String id;

    private Integer sort;

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
