package com.knife.serviceedu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.knife.serviceedu.domain.dto.EduTeacherDto;
import com.knife.serviceedu.domain.entity.EduTeacherDO;
import com.knife.serviceedu.domain.vo.EduTeacherVo;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
public interface EduTeacherService extends IService<EduTeacherDO> {
    /**
     * @author 风烛
     * @date 2021年2月16日 16:12
     * @Description 新增一名教师
     * @Param * @param teacher:老师类
     * @return: int
     **/
    boolean addTeacher(EduTeacherDto teacher);

    /**
     * @author 风烛
     * @date 2021年2月16日 16:23
     * @Description 删除一名教师
     * @Param * @param id:教师id
     * @return: int
     **/
    boolean deleteTeacher(String id);

    /**
     * @author 风烛
     * @date 2021年2月16日 21:01
     * @Description 根据id删除一组教师
     * @Param * @param ids: 教师数组
     * @return: int
     **/
    boolean deleteTeachers(List<String> ids);

    /**
     * @author 风烛
     * @date 2021年2月16日 21:24
     * @Description 根据id对教师数据进行修改
     * @Param * @param teacher:  教师类
     * @return: int
     **/
    boolean updateTeacherById(EduTeacherDto teacher);

    /**
     * @author 风烛
     * @date 2021年2月16日 21:31
     * @Description 根据id查找教师
     * @Param * @param id:  教师id
     * @return: com.knife.serviceedu.domain.entity.EduTeacherDO
     **/
    EduTeacherVo selectByTeacher(String id);

    /**
     * @author 风烛
     * @date 2021年2月16日 21:33
     * @Description 根据id集合查找一组教师
     * @Param * @param ids:  教师id集合
     * @return: java.util.List<com.knife.serviceedu.domain.entity.EduTeacherDO>
     **/
    List<EduTeacherVo> selectByTeachers(List<String> ids);

    /**
     * @author 风烛
     * @date 2021年2月16日 22:02
     * @Description 无条件分页查询
     * @Param * @param page:  分页数据
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.knife.serviceedu.domain.entity.EduTeacherDO>
     **/
    IPage<EduTeacherVo> selectTeacherPage(Page page);


}
