package com.knife.serviceedu.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.knife.commonutil.util.ResponseBean;
import com.knife.serviceedu.domain.dto.EduTeacherDto;
import com.knife.serviceedu.domain.entity.EduTeacherDO;
import com.knife.serviceedu.domain.vo.EduTeacherVo;
import com.knife.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author 风烛
 * @date 2021-02-17 10:57
 */
@Api(tags = "教师管理接口")
@RestController
@RequestMapping("teacher")
public class TeacherAdminController {

    @Autowired
    EduTeacherService eduTeacherService;

    @PostMapping
    @ApiOperation("新增接口")
    public ResponseBean addTeacher(
            @Valid @ApiParam("老师类") @RequestBody EduTeacherDto teacher){
        eduTeacherService.addTeacher(teacher);
        return ResponseBean.succ("添加成功");
    }

    @DeleteMapping
    @ApiOperation("删除一名教师")
    public ResponseBean deleteTeacher(@RequestParam("id")
                                      @ApiParam("教师id")
                                      @NotBlank String id){
        eduTeacherService.deleteTeacher(id);
        return ResponseBean.succ("删除成功");
    }

    @DeleteMapping("/list")
    @ApiOperation("删除一组教师")
    public ResponseBean deleteTeachers(@RequestParam("ids")
                                       @ApiParam("教师id集合")
                                       @NotBlank List<String> ids){
        eduTeacherService.deleteTeachers(ids);
        return ResponseBean.succ("删除成功");
    }

    @PutMapping
    @ApiOperation("根据id修改一名教师的信息")
    public ResponseBean updateTeacherByid( @ApiParam("老师类")
                                          @RequestBody EduTeacherDto teacher) {
        eduTeacherService.updateTeacherById(teacher);
        return ResponseBean.succ("修改成功");
    }

    @GetMapping
    @ApiOperation("根据id查找一名教师的信息")
    public ResponseBean selectByTeacher(@RequestParam("id")
                                        @ApiParam("教师id")
                                        @NotBlank   String id) {
        EduTeacherVo teacher = eduTeacherService.selectByTeacher(id);
        return ResponseBean.succ("查找成功", teacher);
    }

    @GetMapping("/teachers")
    @ApiOperation("根据id集合查找一组教师的信息")
    public ResponseBean selectByTeachers (@RequestParam("ids")
                                          @ApiParam("教师id集合")
                                          @NotBlank List <String> ids) {
        return ResponseBean.succ("查找成功", eduTeacherService.selectByTeachers(ids));
    }

    @GetMapping("/teacherPage")
    @ApiOperation("分页查看教师信息")
    public ResponseBean selectTeacherPage ( @RequestParam("pageCount") @ApiParam("当前页数") @NotBlank int pageCount,
                                            @RequestParam("pageNum") @ApiParam("页面数据量") @NotBlank int pageNum){
        Page<EduTeacherDO> page = new Page<>(pageCount, pageNum);
        return ResponseBean.succ("查找成功", eduTeacherService.selectTeacherPage(page));
    }
}
