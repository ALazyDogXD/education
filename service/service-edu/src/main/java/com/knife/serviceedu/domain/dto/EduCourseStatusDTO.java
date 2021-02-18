package com.knife.serviceedu.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Mr_W
 * @date 2021/2/18 13:43
 * @description 课程状态 dto
 */
@ApiModel("课程状态 dto")
public class EduCourseStatusDTO {

    @ApiModelProperty(value = "课程 id 集合")
    private List<String> ids;

    @NotNull(message = "课程状态不可为空")
    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
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
