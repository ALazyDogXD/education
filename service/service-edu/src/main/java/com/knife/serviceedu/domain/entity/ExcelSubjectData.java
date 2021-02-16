package com.knife.serviceedu.domain.entity;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author Mr_W
 * @date 2021/2/16 17:04
 * @description: 科目 excel 模板类
 */
public class ExcelSubjectData {

    @ExcelProperty(value = "一级学科", index = 0)
    private String levelOneSubjectName;

    @ExcelProperty(value = "二级学科", index = 1)
    private String levelTwoSubjectName;

    public String getLevelOneSubjectName() {
        return levelOneSubjectName;
    }

    public void setLevelOneSubjectName(String levelOneSubjectName) {
        this.levelOneSubjectName = levelOneSubjectName;
    }

    public String getLevelTwoSubjectName() {
        return levelTwoSubjectName;
    }

    public void setLevelTwoSubjectName(String levelTwoSubjectName) {
        this.levelTwoSubjectName = levelTwoSubjectName;
    }

    @Override
    public String toString() {
        return "ExcelSubjectData{" +
                "levelOneSubjectName='" + levelOneSubjectName + '\'' +
                ", levelTwoSubjectName='" + levelTwoSubjectName + '\'' +
                '}';
    }
}
