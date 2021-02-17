package com.knife.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knife.servicebase.entity.ServiceException;
import com.knife.serviceedu.domain.dto.EduTeacherDto;
import com.knife.serviceedu.domain.entity.EduTeacherDO;
import com.knife.serviceedu.domain.vo.EduTeacherVo;
import com.knife.serviceedu.mapper.EduTeacherMapper;
import com.knife.serviceedu.service.EduTeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public boolean deleteTeachers(List<String> ids) {
        List<EduTeacherDO> li = new ArrayList<>();
        for(String e : ids){
            EduTeacherDO convert = new EduTeacherDO();
            convert.setId(e);
            convert.setDeleted(true);
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
        int result = baseMapper.updateById(new EduTeacherDO() {{
            setId(teacher.getId());
            setName(teacher.getName());
            setIntro(teacher.getIntro());
            setCareer(teacher.getCareer());
            setLevel(teacher.getLevel());
            setAvatar(teacher.getAvatar());
            setDeleted(false);
            setGmtModified(LocalDateTime.now());
        }});
        if(result < 0){
            LOGGER.error("教师信息修改失败");
            throw new ServiceException("修改教师信息失败");
        }
        return true;
    }

    @Override
    public EduTeacherVo selectByTeacher(String id) {
        EduTeacherVo result =  baseMapper.selectById(id).convert();
        if (result != null) {
            return result;
        }
        LOGGER.debug("没找到该教师");
        return null;
    }

    @Override
    public List<EduTeacherVo> selectByTeachers(List<String> ids) {
        List<EduTeacherDO> result = baseMapper.selectBatchIds(ids);
        if (result != null) {
            List<EduTeacherVo> li = new ArrayList<>();
            for(EduTeacherDO e : result){
                EduTeacherVo convert = e.convert();
                li.add(convert);
            }
            return li;
        }
        LOGGER.debug("查找一群教师--没有符合条件的教师");
        return null;
    }

    @Override
    public IPage<EduTeacherVo> selectTeacherPage(Page<EduTeacherDO> page) {
        IPage<EduTeacherDO> result = baseMapper.selectPage(page, null);
        IPage<EduTeacherVo> li = new Page<>();
        List<EduTeacherVo> temp = new ArrayList<>();
        if (result != null) {
            for(EduTeacherDO e : result.getRecords()){
                EduTeacherVo convert = e.convert();
                temp.add(convert);
            }
            li.setRecords(temp);
            return li;
        }
        LOGGER.debug("教师分页没有信息");
        return null;
    }


}
