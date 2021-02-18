package com.knife.serviceedu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knife.commonutil.exception.EmptyImageException;
import com.knife.commonutil.exception.FileTypeException;
import com.knife.commonutil.exception.ImageSizeOutOfRangeException;
import com.knife.commonutil.util.MinIoUtil;
import com.knife.servicebase.entity.ServiceException;
import com.knife.serviceedu.constant.EduConstant;
import com.knife.serviceedu.domain.dto.EduCourseDTO;
import com.knife.serviceedu.domain.entity.EduCourseDO;
import com.knife.serviceedu.domain.entity.EduSubjectDO;
import com.knife.serviceedu.domain.vo.EduCourseVO;
import com.knife.serviceedu.mapper.EduCourseMapper;
import com.knife.serviceedu.service.EduCourseDescriptionService;
import com.knife.serviceedu.service.EduCourseService;
import com.knife.serviceedu.service.EduSubjectService;
import com.knife.serviceedu.service.EduTeacherService;
import io.minio.errors.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourseDO> implements EduCourseService {

    private static final Logger LOGGER = getLogger(EduCourseServiceImpl.class);

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.port}")
    private String port;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.path.image.course-cover}")
    private String coverPath;

    @Resource
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Resource
    private EduSubjectService eduSubjectService;

    @Resource
    private EduTeacherService eduTeacherService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCourse(MultipartFile cover, EduCourseDTO course) {
        checkCourse(course);
        EduCourseDO courseConverted = course.convert();
        save(courseConverted
                .setCover("")
                .setBuyCount(0L)
                .setViewCount(0L)
                .setVersion(0L)
                .setIsDeleted(0)
                .setGmtCreate(LocalDateTime.now())
                .setGmtModified(LocalDateTime.now()));
        // 添加课程简介
        eduCourseDescriptionService.add(courseConverted.getId(), course.getDescription());
        // 添加课程封面
        addCoverPath(courseConverted, cover);
    }

    /**
     * 检查 course 参数是否合法
     *
     * @param course 课程
     */
    private void checkCourse(EduCourseDTO course) {
        EduSubjectDO subject = eduSubjectService.getById(course.getSubjectId());
        if (Objects.isNull(subject) || !course.getSubjectParentId().equals(subject.getParentId()) || "0".equals(course.getSubjectParentId())) {
            throw new ServiceException("无效的课程 id");
        }
        if (Objects.isNull(eduTeacherService.getById(course.getTeacherId()))) {
            throw new ServiceException("无效的教师 id");
        }
    }

    /**
     * 添加课程封面
     *
     * @param course 课程
     * @param cover  封面
     */
    private void addCoverPath(EduCourseDO course, MultipartFile cover) {
        try {
            String contentType = getImageContentType(cover);
            // 上传图片文件
            MinIoUtil.upload(bucketName, coverPath + course.getId() + cover.getOriginalFilename(), cover.getInputStream(), contentType);
            updateById(course.setCover(endpoint + ":" + port + "/" + bucketName + coverPath + course.getId() + cover.getOriginalFilename()));
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InsufficientDataException | InternalException | NoResponseException | InvalidBucketNameException | XmlPullParserException | ErrorResponseException | RegionConflictException | InvalidArgumentException | InvalidPortException | InvalidEndpointException e) {
            LOGGER.error("课程添加失败", e);
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

    @Override
    public IPage<EduCourseVO> getList(int page, int size, String order) {
        IPage<EduCourseDO> courses = baseMapper.selectPage(new Page<>(page, size), new QueryWrapper<EduCourseDO>()
                .eq("status", EduConstant.COURSE_NORMAL)
                .eq("is_deleted", false)
                .orderByDesc(StringUtils.isNotBlank(order), order));
        return buildPage(courses);
    }

    /**
     * 获取 vo 分页数据
     * @param courses do 分页数据
     * @return vo 分页数据
     */
    private IPage<EduCourseVO> buildPage(IPage<EduCourseDO> courses) {
        IPage<EduCourseVO> courseViews = new Page<>();

        BeanUtil.copyProperties(courses, courseViews);

        courseViews.setRecords(courses.getRecords().stream().map(course -> {
            EduCourseVO convert = course.convert();
            convert.setDescription(eduCourseDescriptionService.getById(course.getId()).getDescription());
            return convert;
        }).collect(Collectors.toList()));

        return courseViews;
    }

}
