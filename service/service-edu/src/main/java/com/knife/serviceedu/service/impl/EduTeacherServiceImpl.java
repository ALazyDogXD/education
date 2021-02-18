package com.knife.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knife.commonutil.exception.EmptyImageException;
import com.knife.commonutil.exception.FileTypeException;
import com.knife.commonutil.exception.ImageSizeOutOfRangeException;
import com.knife.commonutil.util.MinIoUtil;
import com.knife.servicebase.entity.ServiceException;
import com.knife.serviceedu.domain.dto.EduTeacherDTO;
import com.knife.serviceedu.domain.entity.EduCourseDO;
import com.knife.serviceedu.domain.entity.EduTeacherDO;
import com.knife.serviceedu.domain.vo.EduTeacherVO;
import com.knife.serviceedu.mapper.EduTeacherMapper;
import com.knife.serviceedu.service.EduTeacherService;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.port}")
    private String port;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.path.image.course-cover}")
    private String coverPath;


    @Override
    public boolean addTeacher(EduTeacherDTO teacher, MultipartFile course) {
        LOGGER.debug("教师: [{}]", teacher.convert());
        EduTeacherDO teacherDO = teacher.convert()
                .setDeleted(false)
                .setGmtCreate(LocalDateTime.now())
                .setGmtModified(LocalDateTime.now());
        addCoverPath(teacherDO, course, true);
        return true;
    }

    @Override
    public boolean deleteTeacher(String id) {
        EduTeacherDO teachers = new EduTeacherDO();
        teachers.setId(id);
        teachers.setDeleted(true);
        int result = baseMapper.updateById(teachers);
        if (result < 0) {
            LOGGER.error("教师删除失败");
            throw new ServiceException("删除教师失败");
        }
        return true;
    }

    @Override
    public boolean deleteTeachers(List<String> ids) {
        List<EduTeacherDO> li = new ArrayList<>();
        for (String e : ids) {
            EduTeacherDO convert = new EduTeacherDO();
            convert.setId(e);
            convert.setDeleted(true);
            li.add(convert);
        }
        boolean result = saveOrUpdateBatch(li);
        if (result) {
            return true;
        }
        LOGGER.error("教师删除失败");
        throw new ServiceException("删除教师失败");
    }

    @Override
    public boolean updateTeacherById(EduTeacherDTO teacher) {
        EduTeacherDO teacherDO = teacher.convert().setGmtModified(LocalDateTime.now());
        addCoverPath(teacherDO, teacher.getAvatar(), false);
        return true;
    }

    @Override
    public EduTeacherVO selectByTeacher(String id) {
        EduTeacherVO result = baseMapper.selectById(id).convert();
        if (result != null) {
            return result;
        }
        LOGGER.debug("没找到该教师");
        return null;
    }

    @Override
    public List<EduTeacherVO> selectByTeachers(List<String> ids) {
        List<EduTeacherDO> result = baseMapper.selectBatchIds(ids);
        if (result != null) {
            List<EduTeacherVO> li = new ArrayList<>();
            for (EduTeacherDO e : result) {
                EduTeacherVO convert = e.convert();
                li.add(convert);
            }
            return li;
        }
        LOGGER.debug("查找一群教师--没有符合条件的教师");
        return null;
    }

    @Override
    public IPage<EduTeacherVO> selectTeacherPage(Page<EduTeacherDO> page) {
        IPage<EduTeacherDO> result = baseMapper.selectPage(page, null);
        IPage<EduTeacherVO> li = new Page<>();
        List<EduTeacherVO> temp = new ArrayList<>();
        if (result != null) {
            for (EduTeacherDO e : result.getRecords()) {
                EduTeacherVO convert = e.convert();
                temp.add(convert);
            }
            li.setRecords(temp);
            return li;
        }
        LOGGER.debug("教师分页没有信息");
        return null;
    }

    /**
     * 教师图片信息处理
     *
     * @param course 教师类
     * @param cover  图片信息
     * @param choice 判断是新增还是修改
     * @return void
     */
    private void addCoverPath(EduTeacherDO course, MultipartFile cover, boolean choice) {
        try {
            String contentType = getImageContentType(cover);
            // 上传图片文件
            MinIoUtil.upload(bucketName, coverPath + course.getId() + cover.getOriginalFilename(), cover.getInputStream(), contentType);
            if (choice) {
                save(course.setAvatar(endpoint + ":" + port + "/" + bucketName + coverPath + course.getId() + cover.getOriginalFilename()));
            } else {
                updateById(course.setAvatar(endpoint + ":" + port + "/" + bucketName + coverPath + course.getId() + cover.getOriginalFilename()));
            }
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InsufficientDataException | InternalException | NoResponseException | InvalidBucketNameException | XmlPullParserException | ErrorResponseException | RegionConflictException | InvalidArgumentException | InvalidPortException | InvalidEndpointException e) {
            LOGGER.error("教师添加失败", e);
            throw new ServiceException("课程添加失败");
        }
    }

    /**
     * 获取图片文件媒体格式
     *
     * @param cover 图片文件
     * @return 媒体格式
     */
    private String getImageContentType(MultipartFile cover) {
        try {
            return MinIoUtil.getImageContentType(cover);
        } catch (IOException e) {
            LOGGER.error("文件读写异常", e);
            throw new ServiceException("不支持的文件格式");
        } catch (EmptyImageException e) {
            LOGGER.error("图片为空", e);
            throw new ServiceException("请选择要上传的图片");
        } catch (ImageSizeOutOfRangeException e) {
            LOGGER.error("图片大小超范围", e);
            throw new ServiceException("图片文件不得大于 2 MB");
        } catch (FileTypeException e) {
            LOGGER.error("文件类型错误", e);
            throw new ServiceException("文件类型错误");
        }
    }
}
