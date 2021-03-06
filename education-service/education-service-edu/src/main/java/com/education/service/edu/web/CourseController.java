package com.education.service.edu.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.education.service.base.entity.ResponseData;
import com.education.service.base.entity.ResponseMsg;
import com.education.service.base.strategy.CreateDataTransferObject;
import com.education.service.base.strategy.UpdateDataTransferObject;
import com.education.service.base.web.BaseController;
import com.education.service.edu.domain.dto.EduCourseDTO;
import com.education.service.edu.domain.dto.EduCourseStatusDTO;
import com.education.service.edu.domain.vo.EduCourseVO;
import com.education.service.edu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Mr_W
 * @date 2021/2/17 18:50
 * @description 课程管理接口
 */
@Api(tags = "课程管理接口")
@RestController
@RequestMapping("course")
public class CourseController extends BaseController {

    private static final Logger LOGGER = getLogger(CourseController.class);

    private final EduCourseService eduCourseService;

    public CourseController(EduCourseService eduCourseService) {
        this.eduCourseService = eduCourseService;
    }

    @PostMapping
    @ApiOperation("添加课程")
    public ResponseMsg create(@Validated({CreateDataTransferObject.class, Default.class}) EduCourseDTO course) {
        LOGGER.debug("课程参数: [{}]", course);
        eduCourseService.add(course.getCover(), course);
        return ResponseMsg.success("课程添加成功");
    }

    @GetMapping
    @ApiOperation("获取课程列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页个数", defaultValue = "10"),
            @ApiImplicitParam(name = "orderByColumn", value = "排序字段"),
            @ApiImplicitParam(name = "isDesc", value = "是否降序"),
            @ApiImplicitParam(name = "title", value = "课程名称"),
            @ApiImplicitParam(name = "teacherId", value = "教师 id"),
            @ApiImplicitParam(name = "subjectParentId", value = "一级学科 id"),
            @ApiImplicitParam(name = "subjectId", value = "二级学科 id")
    })
    public ResponseData<IPage<EduCourseVO>> read(@RequestParam(value = "title", required = false) String title,
                                                 @RequestParam(value = "teacherId", required = false) String teacherId,
                                                 @RequestParam(value = "subjectParentId", required = false) String subjectParentId,
                                                 @RequestParam(value = "subjectId", required = false) String subjectId) {
        LOGGER.debug("课程名称: [{}], 教师 id: [{}], 一级学科 id: [{}], 二级学科 id: [{}]", title, teacherId, subjectParentId, subjectId);
        return ResponseData.success(eduCourseService.getList(getPage(), title, teacherId, subjectParentId, subjectId));
    }

    @PutMapping
    @ApiOperation("修改课程")
    public ResponseMsg update(@Validated({UpdateDataTransferObject.class, Default.class}) EduCourseDTO course) {
        LOGGER.debug("课程: [{}]", course);
        eduCourseService.update(course);
        return ResponseMsg.success("修改成功");
    }

    @PutMapping("status")
    @ApiOperation("修改课程状态")
    public ResponseMsg updateStatus(@RequestBody @Validated EduCourseStatusDTO course) {
        LOGGER.debug("课程 id: [{}], 状态: [{}]", Arrays.toString(course.getIds().toArray()), course.getStatus());
        eduCourseService.updateStatus(course.getIds(), course.getStatus());
        return ResponseMsg.success("修改成功");
    }

    @DeleteMapping
    @ApiOperation("删除课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "id 数组", dataTypeClass = List.class)
    })
    public ResponseMsg delete(@RequestParam("ids") @Validated @Size(min = 1, message = "请选择至少一门课程") List<String> ids) {
        LOGGER.debug("课程 id: [{}]", Arrays.toString(ids.toArray()));
        eduCourseService.remove(ids);
        return ResponseMsg.success("删除成功");
    }

}
