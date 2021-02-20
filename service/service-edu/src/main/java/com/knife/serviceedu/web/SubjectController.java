package com.knife.serviceedu.web;

import com.knife.servicebase.entity.ResponseBean;
import com.knife.serviceedu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author Mr_W
 * @date 2021/2/16 15:48
 * @description: 学科管理接口
 */
@Api(tags = "学科管理接口")
@RestController
@RequestMapping("subject")
public class SubjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectController.class);

    @Resource
    private EduSubjectService eduSubjectService;

    @PostMapping
    @ApiOperation("导入科目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "excel 文件", dataTypeClass = MultipartFile.class)
    })
    public ResponseBean add(MultipartFile file) {
        eduSubjectService.importSubjectFile(file);
        return ResponseBean.succ("科目导入成功");
    }

    @GetMapping
    @ApiOperation("获取科目树")
    public ResponseBean getTree() {
        return ResponseBean.succ(eduSubjectService.getTree());
    }

}
