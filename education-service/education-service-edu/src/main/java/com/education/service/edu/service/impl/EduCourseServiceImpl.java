package com.education.service.edu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.education.rpc.minio.service.MinIoFileService;
import com.education.service.base.entity.ServiceException;
import com.education.service.edu.domain.dto.EduCourseDTO;
import com.education.service.edu.domain.entity.EduCourseDO;
import com.education.service.edu.domain.entity.EduCourseDescriptionDO;
import com.education.service.edu.domain.entity.EduSubjectDO;
import com.education.service.edu.domain.vo.EduCourseVO;
import com.education.service.edu.mapper.EduCourseMapper;
import com.education.service.edu.service.*;
import com.education.service.edu.util.ImageUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.education.service.edu.constant.EduConstant.COURSE_DRAFT;
import static com.education.service.edu.constant.EduConstant.COURSE_NORMAL;
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

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.path.image.course-cover}")
    private String coverPath;

    private EduCourseDescriptionService eduCourseDescriptionService;

    private EduSubjectService eduSubjectService;

    private EduTeacherService eduTeacherService;

    private EduVideoService eduVideoService;

    private EduChapterService eduChapterService;

    @DubboReference
    private MinIoFileService minIoFileService;

    public EduCourseServiceImpl(EduCourseDescriptionService eduCourseDescriptionService,
                                EduSubjectService eduSubjectService,
                                EduTeacherService eduTeacherService,
                                EduVideoService eduVideoService,
                                EduChapterService eduChapterService) {
        this.eduCourseDescriptionService = eduCourseDescriptionService;
        this.eduSubjectService = eduSubjectService;
        this.eduTeacherService = eduTeacherService;
        this.eduVideoService = eduVideoService;
        this.eduChapterService = eduChapterService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MultipartFile cover, EduCourseDTO course) {
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
            throw ServiceException.serviceException("无效的课程 id").build();
        }
        if (Objects.isNull(eduTeacherService.getById(course.getTeacherId()))) {
            throw ServiceException.serviceException("无效的教师 id").build();
        }
    }

    /**
     * 添加课程封面
     *
     * @param course 课程
     * @param cover  封面
     */
    private void addCoverPath(EduCourseDO course, MultipartFile cover) {
        String url;
        try(InputStream in = cover.getInputStream()) {
            url = minIoFileService.uploadThumbnail(
                    bucketName,
                    getImageContentType(cover),
                    coverPath,
                    course.getId() + Objects.requireNonNull(cover.getOriginalFilename()).substring(cover.getOriginalFilename().lastIndexOf(".")),
                    IOUtils.toByteArray(in));
        } catch (IOException e) {
            throw ServiceException.serviceException("文件读写异常", e).alertMessage("文件上传失败").build();
        }
        updateById(course.setCover(url));
    }

    /**
     * 获取图片文件媒体格式
     *
     * @param cover 图片文件
     * @return 媒体格式
     */
    private String getImageContentType(MultipartFile cover) {
        try {
            return ImageUtil.getThumbnailContentType(cover);
        } catch (IOException e) {
            throw ServiceException.serviceException("文件读写异常", e).alertMessage("文件上传失败").build();
        }
    }

    @Override
    public IPage<EduCourseVO> getList(IPage<EduCourseDO> page, String title, String teacherId, String subjectParentId, String subjectId) {
        IPage<EduCourseDO> courses = baseMapper.selectPage(page, new LambdaQueryWrapper<EduCourseDO>()
                .eq(EduCourseDO::getStatus, COURSE_NORMAL)
                .eq(EduCourseDO::getIsDeleted, false)
                .like(StringUtils.isNotBlank(title), EduCourseDO::getTitle, title)
                .eq(StringUtils.isNotBlank(teacherId), EduCourseDO::getTeacherId, teacherId)
                .eq(StringUtils.isNotBlank(subjectParentId), EduCourseDO::getSubjectParentId, subjectParentId)
                .eq(StringUtils.isNotBlank(subjectId), EduCourseDO::getSubjectId, subjectId));
        return buildPage(courses);
    }

    /**
     * 获取 vo 分页数据
     *
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(EduCourseDTO course) {
        checkCourse(course);
        // 更新封面
        if (Objects.nonNull(course.getCover())) {
            MultipartFile cover = course.getCover();
            updateCover(cover, course.getId() + Objects.requireNonNull(cover.getOriginalFilename())
                    .substring(cover.getOriginalFilename().lastIndexOf(".")));
        }
        // 更新课程描述
        if (StringUtils.isNotBlank(course.getDescription())) {
            eduCourseDescriptionService.updateById(new EduCourseDescriptionDO() {{
                setId(course.getId());
                setDescription(course.getDescription());
                setGmtModified(LocalDateTime.now());
            }});
        }
        // 更新课程信息
        updateById(course.convert().setCover(null));
    }

    /**
     * 更新封面
     *
     * @param cover 封面文件
     */
    private void updateCover(MultipartFile cover, String coverName) {
        try {
            minIoFileService.uploadThumbnail(bucketName, getImageContentType(cover), coverPath, coverName, IOUtils.toByteArray(cover.getInputStream()));
        } catch (IOException e) {
            throw ServiceException.serviceException("文件读写异常", e).alertMessage("文件上传失败").build();
        }
    }

    @Override
    public void updateStatus(List<String> ids, Boolean status) {
        updateBatchById(ids.stream().map(id -> new EduCourseDO() {{
            setId(id);
            setStatus(status ? COURSE_NORMAL : COURSE_DRAFT);
        }}).collect(Collectors.toList()));
    }

    @Override
    public void remove(List<String> ids) {
        checkIdsBeforeRemove(ids);
        baseMapper.deleteBatchIds(ids);
    }

    /**
     * 检查 id 集合
     *
     * @param ids id 集合
     */
    private void checkIdsBeforeRemove(List<String> ids) {
        if (!ids.stream().allMatch(id -> eduChapterService.getByChapterId(id).isEmpty() && eduVideoService.getByCourseId(id).isEmpty())) {
            throw ServiceException.serviceException("不可删除含有章节或视频的课程").build();
        }
    }
}
