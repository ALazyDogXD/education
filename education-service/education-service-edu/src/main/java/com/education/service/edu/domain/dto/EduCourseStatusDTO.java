package com.education.service.edu.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Mr_W
 * @date 2021/2/18 13:43
 * @description 课程状态 dto
 */
@ApiModel("课程状态 dto")
public class EduCourseStatusDTO {

    @ApiModelProperty(value = "课程 ID 集合")
    @Size(min = 1, message = "请选择至少一门课程")
    private List<String> ids;

    @NotNull(message = "课程状态不可为空")
    @ApiModelProperty(value = "课程状态 Draft 未发布  Normal 已发布")
    private Boolean status;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EduCourseStatusDTO{" +
                "ids=" + ids +
                ", status='" + status + '\'' +
                '}';
    }
}
