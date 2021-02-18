package com.knife.serviceedu.web;

import com.knife.commonutil.util.ResponseBean;
import com.knife.serviceedu.domain.dto.EduCourseDTO;
import com.knife.serviceedu.domain.dto.EduCourseStatusDTO;
import com.knife.serviceedu.domain.entity.EduSubjectDO;
import com.knife.serviceedu.service.EduCourseService;
import com.knife.serviceedu.strategy.CreateDataTransferObject;
import com.knife.serviceedu.strategy.UpdateDataTransferObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Mr_W
 * @date 2021/2/17 18:50
 * @description: 课程管理接口
 */
@Api(tags = "课程管理接口")
@RestController
@RequestMapping("course")
public class CourseController {

    private static final Logger LOGGER = getLogger(CourseController.class);

    @Resource
    private EduCourseService eduCourseService;

    @PostMapping
    @ApiOperation("添加课程")
    public ResponseBean add(@Validated(CreateDataTransferObject.class) EduCourseDTO course) {
        LOGGER.debug("课程参数: [{}]", course);
        eduCourseService.add(course.getCover(), course);
        return ResponseBean.succ("课程添加成功");
    }

    @GetMapping("list")
    @ApiOperation("获取课程列表")
    public ResponseBean getList(@RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size,
                                @RequestParam(value = "order", required = false) String order) {
        LOGGER.debug("当前页数: [{}], 每页数量: [{}], 排序字段: [{}]", page, size, order);
        return ResponseBean.succ(eduCourseService.getList(page, size, order));
    }

    @PutMapping()
    @ApiOperation("修改课程")
    public ResponseBean update(@Validated(UpdateDataTransferObject.class) EduCourseDTO course) {
        LOGGER.debug("课程: [{}]", course);
        eduCourseService.update(course);
        return ResponseBean.succ("修改成功");
    }

    @PutMapping("status")
    @ApiOperation("修改课程状态")
    public ResponseBean updateStatus(@RequestBody EduCourseStatusDTO course) {
        LOGGER.debug("课程 id: [{}], 状态: [{}]", Arrays.toString(course.getIds().toArray()), course.getStatus());
        eduCourseService.updateStatus(course.getIds(), course.getStatus());
        return ResponseBean.succ("修改成功");
    }

}
