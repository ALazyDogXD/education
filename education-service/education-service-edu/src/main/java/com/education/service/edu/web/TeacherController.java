package com.education.service.edu.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.education.service.base.entity.ResponseData;
import com.education.service.base.entity.ResponseMsg;
import com.education.service.base.strategy.CreateDataTransferObject;
import com.education.service.base.strategy.UpdateDataTransferObject;
import com.education.service.base.web.BaseController;
import com.education.service.edu.domain.dto.EduTeacherDTO;
import com.education.service.edu.domain.vo.EduTeacherVO;
import com.education.service.edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.Arrays;
import java.util.List;

/**
 * @author 风烛
 * @date 2021-02-17 10:57
 */
@Api(tags = "教师管理接口")
@RestController
@RequestMapping("teacher")
public class TeacherController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);

    @Resource
    private EduTeacherService eduTeacherService;

    @PostMapping
    @ApiOperation("新增教师接口")
    public ResponseMsg create(@Validated({CreateDataTransferObject.class, Default.class}) EduTeacherDTO teacher) {
        LOGGER.debug("教师入参: [{}]", teacher);
        eduTeacherService.insert(teacher, teacher.getAvatar());
        return ResponseMsg.success("添加成功");
    }

    @DeleteMapping
    @ApiOperation("删除一组教师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "教师id集合")
    })
    public ResponseMsg delete(@Validated @RequestParam(value = "ids") @Size(min = 1, message = "请选择要删除的教师") List<String> ids) {
        LOGGER.debug("教师 id: [{}]", Arrays.toString(ids.toArray()));
        eduTeacherService.removeByIds(ids);
        return ResponseMsg.success("删除成功");
    }

    @PutMapping
    @ApiOperation("根据 id 修改一名教师的信息")
    public ResponseMsg update(@Validated({UpdateDataTransferObject.class, Default.class}) EduTeacherDTO teacher) {
        LOGGER.debug("教师入参: [{}]", teacher);
        eduTeacherService.update(teacher);
        return ResponseMsg.success("修改成功");
    }

    @GetMapping
    @ApiOperation("分页查看教师信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页个数", defaultValue = "10"),
            @ApiImplicitParam(name = "orderByColumn", value = "排序字段"),
            @ApiImplicitParam(name = "isDesc", value = "是否降序"),
            @ApiImplicitParam(name = "name", value = "教师名称")
    })
    public ResponseData<IPage<EduTeacherVO>> read(@RequestParam(value = "title", required = false) String name) {
        LOGGER.debug("教师姓名: [{}]", name);
        return ResponseData.success(eduTeacherService.select(getPage(), name));
    }
}
