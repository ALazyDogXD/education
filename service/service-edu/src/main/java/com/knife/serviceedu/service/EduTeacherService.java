package com.knife.serviceedu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.knife.serviceedu.domain.entity.EduTeacherDO;

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
    int addTeacher(EduTeacherDO teacher);

    /**
     * @author 风烛
     * @date 2021年2月16日 16:23
     * @Description 删除一名教师
     * @Param * @param id:教师id
     * @return: int
     **/
    int deleteTeacher(int id);

    /**
     * @author 风烛
     * @date 2021年2月16日 21:01
     * @Description 根据id删除一组教师
     * @Param * @param ids: 教师数组
     * @return: int
     **/
    int deleteTeachers(List<String> ids);

    /**
     * @author 风烛
     * @date 2021年2月16日 21:04
     * @Description 根据特定条件进行删除(where)
     * @Param * @param map:where条件键值对
     * @return: int
     **/
    int deleteByMap(HashMap map);

    /**
     * @author 风烛
     * @date 2021年2月16日 21:24
     * @Description 根据id对教师数据进行修改
     * @Param * @param teacher:  教师类
     * @return: int
     **/
    int updateTeacherById(EduTeacherDO teacher);

    /**
     * @author 风烛
     * @date 2021年2月16日 21:31
     * @Description 根据id查找教师
     * @Param * @param id:  教师id
     * @return: com.knife.serviceedu.domain.entity.EduTeacherDO
     **/
    EduTeacherDO selectByTeacher(int id);

    /**
     * @author 风烛
     * @date 2021年2月16日 21:33
     * @Description 根据id集合查找一组教师
     * @Param * @param ids:  教师id集合
     * @return: java.util.List<com.knife.serviceedu.domain.entity.EduTeacherDO>
     **/
    List<EduTeacherDO> selectByTeachers(List<String> ids);

    /**
     * @author 风烛
     * @date 2021年2月16日 21:37
     * @Description 根据map条件进行查询
     * @Param * @param map:  查询的map
     * @return: java.util.List<com.knife.serviceedu.domain.entity.EduTeacherDO>
     **/
    List<EduTeacherDO> selectTeacherMap(HashMap map);

    /**
     * @author 风烛
     * @date 2021年2月16日 22:02
     * @Description 无条件分页查询
     * @Param * @param page:  分页数据
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.knife.serviceedu.domain.entity.EduTeacherDO>
     **/
    IPage<EduTeacherDO> selectTeacherPage(Page page);


}
