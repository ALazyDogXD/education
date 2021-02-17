package com.knife.serviceedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knife.commonutil.util.MinIoUtil;
import com.knife.servicebase.entity.ServiceException;
import com.knife.serviceedu.constant.EduConstant;
import com.knife.serviceedu.domain.dto.EduCourseDTO;
import com.knife.serviceedu.domain.entity.EduCourseDO;
import com.knife.serviceedu.domain.entity.EduSubjectDO;
import com.knife.serviceedu.mapper.EduCourseMapper;
import com.knife.serviceedu.service.EduCourseDescriptionService;
import com.knife.serviceedu.service.EduCourseService;
import com.knife.serviceedu.service.EduSubjectService;
import com.knife.serviceedu.service.EduTeacherService;
import com.sun.xml.bind.v2.model.core.ID;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Objects;

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
            //检查文件是否为空
            if (Objects.isNull(cover) || cover.isEmpty()) {
                throw new ServiceException("请选择图片");
            }
            //检查文件大小
            if (cover.getSize() > EduConstant.M2_TO_BYTE) {
                throw new ServiceException("请上传2M以内的图片");
            }
            //检查是否是图片
            if (Objects.isNull(ImageIO.read(cover.getInputStream()))) {
                throw new ServiceException("上传的文件不是图片");
            }
            String suffix = Objects.requireNonNull(cover.getOriginalFilename()).substring(cover.getOriginalFilename().lastIndexOf(".") + 1);
            String contentType = "image/" + ("jpg".equals(suffix) ? "jpeg" : suffix);
            MinIoUtil.upload(bucketName, coverPath + course.getId() + cover.getOriginalFilename(), cover.getInputStream(), contentType);
            updateById(course.setCover(endpoint + ":" + port + "/" + bucketName + coverPath + course.getId() + cover.getOriginalFilename()));
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InsufficientDataException | InternalException | NoResponseException | InvalidBucketNameException | XmlPullParserException | ErrorResponseException | RegionConflictException | InvalidArgumentException | InvalidPortException | InvalidEndpointException e) {
            LOGGER.error("课程添加失败", e);
            throw new ServiceException("课程添加失败");
        }
    }

}
