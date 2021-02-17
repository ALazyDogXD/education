package com.knife.serviceedu.web;

import com.knife.commonutil.util.ResponseBean;
import com.knife.serviceedu.domain.dto.EduCourseDTO;
import com.knife.serviceedu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

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
    public ResponseBean addCourse(@Valid EduCourseDTO course) {
        LOGGER.debug("课程参数: [{}]", course);
        eduCourseService.addCourse(course.getCover(), course);
        return ResponseBean.succ("课程添加成功");
    }

}
