package com.knife.serviceedu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knife.commonutil.exception.EmptyImageException;
import com.knife.commonutil.exception.FileTypeException;
import com.knife.commonutil.exception.ImageSizeOutOfRangeException;
import com.knife.commonutil.util.MinIoUtil;
import com.knife.servicebase.entity.ServiceException;
import com.knife.serviceedu.domain.dto.EduCourseDTO;
import com.knife.serviceedu.domain.entity.EduCourseDO;
import com.knife.serviceedu.domain.entity.EduCourseDescriptionDO;
import com.knife.serviceedu.domain.entity.EduSubjectDO;
import com.knife.serviceedu.domain.vo.EduCourseVO;
import com.knife.serviceedu.mapper.EduCourseMapper;
import com.knife.serviceedu.service.*;
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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.knife.serviceedu.constant.EduConstant.COURSE_DRAFT;
import static com.knife.serviceedu.constant.EduConstant.COURSE_NORMAL;
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

    @Resource
    private EduVideoService eduVideoService;

    @Resource
    private EduChapterService eduChapterService;

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
                .eq("status", COURSE_NORMAL)
                .eq("is_deleted", false)
                .orderByDesc(StringUtils.isNotBlank(order), order));
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
        String cover = null;
        // 更新封面
        if (Objects.nonNull(course.getCover())) {
            String oldCoverPath = getById(course.getId()).getCover();
            oldCoverPath = getById(course.getId()).getCover().substring(oldCoverPath.indexOf(bucketName) + bucketName.length());
            cover = updateCover(course.getCover(), coverPath + course.getId() + course.getCover().getOriginalFilename(), oldCoverPath);
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
        updateById(course.convert().setCover(cover));
    }

    /**
     * 更新封面
     *
     * @param cover   封面文件
     * @param path    新封面文件路径
     * @param oldPath 旧封面文件路径
     * @return 更新后的路径
     */
    private String updateCover(MultipartFile cover, String path, String oldPath) {
        String contentType = getImageContentType(cover);
        try {
            MinIoUtil.removeFile(bucketName, oldPath);
            MinIoUtil.upload(bucketName, path, cover.getInputStream(), contentType);
            return endpoint + ":" + port + "/" + bucketName + path;
        } catch (IOException | InvalidEndpointException | InvalidPortException | InvalidKeyException | NoSuchAlgorithmException | InsufficientDataException | InternalException | NoResponseException | InvalidBucketNameException | XmlPullParserException | ErrorResponseException | RegionConflictException | InvalidArgumentException e) {
            LOGGER.error("图片更新失败", e);
            throw new ServiceException("服务器异常, 请稍后再试");
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
     * @param ids id 集合
     */
    private void checkIdsBeforeRemove(List<String> ids) {
        if (!ids.stream().allMatch(id -> eduChapterService.getByChapterId(id).isEmpty() && eduVideoService.getByCourseId(id).isEmpty())) {
            throw new ServiceException("不可删除含有章节或视频的课程");
        }
    }
}
