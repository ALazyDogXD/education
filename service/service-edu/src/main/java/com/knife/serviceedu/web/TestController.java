package com.knife.serviceedu.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.knife.commonutil.util.ResponseBean;
import com.knife.serviceedu.domain.entity.EduTeacherDO;
import com.knife.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mr_W
 * @date 2021/2/16 13:19
 * @description: controller 测试
 */
@RestController
@RequestMapping("test")
@Api("测试")
public class TestController {

    @Autowired
    EduTeacherService eduTeacherService;

    @GetMapping("/t")
    @ApiOperation("测试接口")   // 接口描述
    public String test(@RequestParam("test") //参数名称
                           @ApiParam("测试")  //参数描述
                           @NotBlank         //参数验证不能为空
                                   String test
                        ) {
        return "test";
    }

    @PostMapping
    @ApiOperation("新增接口")
    public ResponseBean addTeacher(@RequestParam("teacher") @ApiParam("老师类")
                                       @RequestBody EduTeacherDO teacher){
        int num = eduTeacherService.addTeacher(teacher);
        if(num > 0){
            return ResponseBean.succ("添加成功");
        }
        return ResponseBean.fail("添加失败");
    }

    @DeleteMapping
    @ApiOperation("删除一名教师")
    public ResponseBean deleteTeacher(@RequestParam("id")
                                          @ApiParam("教师id")
                                          @NotBlank   int id){
        int num = eduTeacherService.deleteTeacher(id);
        if(num > 0){
            return ResponseBean.succ("删除成功");
        }
        return ResponseBean.fail("删除失败");
    }

    @DeleteMapping("/list")
    @ApiOperation("删除一组教师")
    public ResponseBean deleteTeachers(@RequestParam("ids")
                                           @ApiParam("教师id集合")
                                           @NotBlank List<String> ids){
        int num = eduTeacherService.deleteTeachers(ids);
        if(num > 0){
            return ResponseBean.succ("删除成功");
        }
        return ResponseBean.fail("删除失败");
    }

    @DeleteMapping("/map")
    @ApiOperation("根据特定条件删除教师")
    public ResponseBean deleteTeacherByMap(@RequestParam("map")
                                       @ApiParam("教师id集合")
                                       @NotBlank HashMap map){
        int num = eduTeacherService.deleteByMap(map);
        if(num > 0){
            return ResponseBean.succ("删除成功");
        }
        return ResponseBean.fail("删除失败");
    }

    @PutMapping
    @ApiOperation("根据id修改一名教师的信息")
    public ResponseBean updateTeacherByid(@RequestParam("teacher") @ApiParam("老师类")
                                              @RequestBody EduTeacherDO teacher) {
        int num = eduTeacherService.updateTeacherById(teacher);
        if (num > 0) {
            return ResponseBean.succ("修改成功");
        }
        return ResponseBean.fail("修改失败");
    }

    @GetMapping
    @ApiOperation("根据id查找一名教师的信息")
    public ResponseBean selectByTeacher(@RequestParam("id")
                                            @ApiParam("教师id")
                                            @NotBlank   int id) {
        EduTeacherDO teacher = eduTeacherService.selectByTeacher(id);
        if (teacher != null) {
            return ResponseBean.succ("查找成功",teacher);
        }
        return ResponseBean.fail("无改老师信息，请核对后再查找");
    }

    @GetMapping("/teachers")
    @ApiOperation("根据id集合查找一组教师的信息")
    public ResponseBean selectByTeachers(@RequestParam("ids")
                                        @ApiParam("教师id集合")
                                        @NotBlank   List<String> ids) {
        List<EduTeacherDO> teachers = eduTeacherService.selectByTeachers(ids);
        if (teachers != null) {
            return ResponseBean.succ("查找成功",teachers);
        }
        return ResponseBean.fail("信息错误没有找到");
    }

    @GetMapping("/teacherMap")
    @ApiOperation("根据map集合查找符合信息的教师")
    public ResponseBean selectByTeacherMap(@RequestParam("map")
                                         @ApiParam("查找条件集合")
                                         @NotBlank   HashMap map) {
        List<EduTeacherDO> teachers = eduTeacherService.selectTeacherMap(map);
        if (teachers != null) {
            return ResponseBean.succ("查找成功",teachers);
        }
        return ResponseBean.fail("没有符合条件的教师");
    }

    @GetMapping("/teacherPage")
    @ApiOperation("分页查看教师信息")
    public ResponseBean selectTeacherPage(@RequestParam("pageCount") @ApiParam("当前页数") @NotBlank int pageCount,
                                            @RequestParam("pageNum") @ApiParam("页面数据量") @NotBlank int pageNum) {
        Page<EduTeacherDO> page = new Page(pageCount, pageNum);
        IPage<EduTeacherDO> teachers = eduTeacherService.selectTeacherPage(page);
        if (teachers != null) {
            return ResponseBean.succ("查找成功",teachers);
        }
        return ResponseBean.fail("信息错误");
    }
}
