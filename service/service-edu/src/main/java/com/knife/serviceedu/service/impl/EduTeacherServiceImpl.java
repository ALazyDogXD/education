package com.knife.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knife.serviceedu.domain.entity.EduTeacherDO;
import com.knife.serviceedu.mapper.EduTeacherMapper;
import com.knife.serviceedu.service.EduTeacherService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacherDO> implements EduTeacherService {


    @Override
    public int addTeacher(EduTeacherDO teacher){
        return baseMapper.insert(teacher);
    }

    @Override
    public int deleteTeacher(int id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public int deleteTeachers(List<String> ids) {
        return baseMapper.deleteBatchIds(ids);
    }

    @Override
    public int deleteByMap(HashMap map) {
        return baseMapper.deleteByMap(map);
    }

    @Override
    public int updateTeacherById(EduTeacherDO teacher) {
        return baseMapper.updateById(teacher);
    }

    @Override
    public EduTeacherDO selectByTeacher(int id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<EduTeacherDO> selectByTeachers(List<String> ids) {
        return baseMapper.selectBatchIds(ids);
    }

    @Override
    public List<EduTeacherDO> selectTeacherMap(HashMap map) {
        return baseMapper.selectByMap(map);
    }

    @Override
    public IPage<EduTeacherDO> selectTeacherPage(Page page) {
        return baseMapper.selectPage(page, null);
    }


}
