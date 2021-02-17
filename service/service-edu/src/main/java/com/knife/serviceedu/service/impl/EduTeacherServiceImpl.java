package com.knife.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knife.commonutil.util.ResponseBean;
import com.knife.servicebase.entity.ServiceException;
import com.knife.serviceedu.domain.dto.EduTeacherDto;
import com.knife.serviceedu.domain.entity.EduTeacherDO;
import com.knife.serviceedu.mapper.EduTeacherMapper;
import com.knife.serviceedu.service.EduTeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(EduSubjectServiceImpl.class);

    @Override
    public boolean addTeacher(EduTeacherDto teacher){
        EduTeacherDO teachers = new EduTeacherDO();
        teachers.setName(teacher.getName());
        teachers.setIntro(teacher.getIntro());
        teachers.setCareer(teacher.getCareer());
        teachers.setLevel(teacher.getLevel());
        teachers.setAvatar(teacher.getAvatar());
        teachers.setDeleted(false);
        teachers.setGmtCreate(LocalDateTime.now());
        teachers.setGmtModified(LocalDateTime.now());
        int result = baseMapper.insert(teachers);
        if(result < 0){
            LOGGER.error("教师添加失败");
            throw new ServiceException("增加新教师失败");
        }
        return true;
    }

    @Override
    public boolean deleteTeacher(String id) {
        EduTeacherDO teachers = new EduTeacherDO();
        teachers.setId(id);
        teachers.setDeleted(true);
        int result = baseMapper.updateById(teachers);
        if(result < 0){
            LOGGER.error("教师删除失败");
            throw new ServiceException("删除教师失败");
        }
        return true;
    }

    @Override
    public boolean deleteTeachers(List<EduTeacherDto> ids) {
        List<EduTeacherDO> li = new ArrayList<EduTeacherDO>();
        for(EduTeacherDto e : ids){
            EduTeacherDO convert = e.convert();
            convert.setDeleted(false);
            li.add(convert);
        }
        boolean result = saveOrUpdateBatch(li);
        if(result){
            return true;
        }
        LOGGER.error("教师删除失败");
        throw new ServiceException("删除教师失败");
    }

    @Override
    public boolean updateTeacherById(EduTeacherDto teacher) {
        EduTeacherDO teachers = new EduTeacherDO();
        teachers.setName(teacher.getName());
        teachers.setIntro(teacher.getIntro());
        teachers.setCareer(teacher.getCareer());
        teachers.setLevel(teacher.getLevel());
        teachers.setAvatar(teacher.getAvatar());
        teachers.setDeleted(false);
        teachers.setGmtModified(LocalDateTime.now());
        int result = baseMapper.updateById(teachers);
        if(result < 0){
            LOGGER.error("教师删除失败");
            throw new ServiceException("删除教师失败");
        }
        return true;
    }

    @Override
    public EduTeacherDO selectByTeacher(String id) {
        EduTeacherDO result =  baseMapper.selectById(id);
        if (result != null) {
            return result;
        }
        LOGGER.debug("没找到该教师");
        return null;
    }

    @Override
    public List<EduTeacherDO> selectByTeachers(List<String> ids) {
        List<EduTeacherDO> result = baseMapper.selectBatchIds(ids);
        if (result != null) {
            return result;
        }
        LOGGER.debug("查找一群教师--没有符合条件的教师");
        return null;
    }

    @Override
    public List<EduTeacherDO> selectTeacherMap(HashMap map) {
        List<EduTeacherDO> result = baseMapper.selectByMap(map);
        if (result != null) {
            return result;
        }
        LOGGER.debug("根据map查询教师--没有符合条件的教师");
        return null;
    }

    @Override
    public IPage<EduTeacherDO> selectTeacherPage(Page page) {
        IPage<EduTeacherDO> result = baseMapper.selectPage(page, null);
        if (result != null) {
            return result;
        }
        LOGGER.debug("根据map查询教师--没有符合条件的教师");
        return null;
    }


}
