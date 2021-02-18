package com.knife.serviceedu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.knife.serviceedu.domain.dto.EduTeacherDTO;
import com.knife.serviceedu.domain.entity.EduTeacherDO;
import com.knife.serviceedu.domain.vo.EduTeacherVO;

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
     * 新增一名教师
     *
     * @param teacher:老师类
     * @return int
     * @author 风烛
     * @date 2021年2月16日 16:12
     **/
    boolean addTeacher(EduTeacherDTO teacher);

    /**
     * 删除一名教师
     *
     * @param id:教师id
     * @return int
     * @author 风烛
     * @date 2021年2月16日 16:23
     **/
    boolean deleteTeacher(String id);

    /**
     * 根据id删除一组教师
     *
     * @param ids: 教师数组
     * @return int
     * @author 风烛
     * @date 2021年2月16日 21:01
     **/
    boolean deleteTeachers(List<String> ids);

    /**
     * 根据id对教师数据进行修改
     *
     * @param teacher: 教师类
     * @return int
     * @author 风烛
     * @date 2021年2月16日 21:24
     **/
    boolean updateTeacherById(EduTeacherDTO teacher);

    /**
     * 根据id查找教师
     *
     * @param id: 教师id
     * @return com.knife.serviceedu.domain.entity.EduTeacherDO
     * @author 风烛
     * @date 2021年2月16日 21:31
     **/
    EduTeacherVO selectByTeacher(String id);

    /**
     * 根据id集合查找一组教师
     *
     * @param ids: 教师id集合
     * @return java.util.List<com.knife.serviceedu.domain.entity.EduTeacherDO>
     * @author 风烛
     * @date 2021年2月16日 21:33
     **/
    List<EduTeacherVO> selectByTeachers(List<String> ids);

    /**
     * 无条件分页查询
     *
     * @param page: 分页数据
     * @return 分页数据
     * @author 风烛
     * @date 2021年2月16日 22:02
     **/
    IPage<EduTeacherVO> selectTeacherPage(Page<EduTeacherDO> page);


}
