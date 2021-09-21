package com.education.service.edu.web;

import com.education.service.base.entity.ResponseData;
import com.education.service.base.entity.ResponseMsg;
import com.education.service.edu.domain.vo.EduSubjectParentVO;
import com.education.service.edu.service.EduSubjectService;
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

import java.util.List;

/**
 * @author Mr_W
 * @date 2021/2/16 15:48
 * @description 学科管理接口
 */
@Api(tags = "学科管理接口")
@RestController
@RequestMapping("subject")
public class SubjectController {

    private final EduSubjectService eduSubjectService;

    public SubjectController(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @PostMapping
    @ApiOperation("导入科目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "excel 文件", dataTypeClass = MultipartFile.class)
    })
    public ResponseMsg importSubject(MultipartFile file) {
        eduSubjectService.importSubjectFile(file);
        return ResponseMsg.success("科目导入成功");
    }

    @GetMapping
    @ApiOperation("获取科目树")
    public ResponseData<List<EduSubjectParentVO>> readTree() {
        return ResponseData.success(eduSubjectService.getTree());
    }

}
