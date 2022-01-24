package com.education.service.edu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.education.rpc.minio.service.MinIoFileService;
import com.education.service.base.entity.ObjectConvert;
import com.education.service.base.entity.ServiceException;
import com.education.service.edu.domain.dto.EduTeacherDTO;
import com.education.service.edu.domain.entity.EduTeacherDO;
import com.education.service.edu.domain.vo.EduTeacherVO;
import com.education.service.edu.mapper.EduTeacherMapper;
import com.education.service.edu.service.EduTeacherService;
import com.education.service.edu.util.ImageUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static com.education.service.base.entity.enums.ResponseEnum.IMAGE_UPLOAD_FAIL;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@Service
@RefreshScope
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacherDO> implements EduTeacherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EduSubjectServiceImpl.class);

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.path.image.course-cover}")
    private String coverPath;

    @DubboReference(mock = "com.education.service.edu.mock.MinIoFileServiceMockImpl")
    private MinIoFileService minIoFileService;

    @Override
    public void insert(EduTeacherDTO teacher, MultipartFile file) {
        LOGGER.debug("教师: [{}]", teacher.convert());
        EduTeacherDO teacherDO = teacher.convert()
                .setDeleted(false)
                .setGmtCreate(LocalDateTime.now())
                .setGmtModified(LocalDateTime.now());
        addCoverPath(teacherDO, file, true);
    }

    @Override
    public void update(EduTeacherDTO teacher) {
        EduTeacherDO teacherDO = teacher.convert().setGmtModified(LocalDateTime.now());
        addCoverPath(teacherDO, teacher.getAvatar(), false);
    }

    @Override
    public IPage<EduTeacherVO> select(IPage<EduTeacherDO> page, String name) {
        page = baseMapper.selectPage(page, new LambdaQueryWrapper<EduTeacherDO>()
                .like(StringUtils.isNotBlank(name), EduTeacherDO::getName, name));
        IPage<EduTeacherVO> result = new Page<>();
        BeanUtil.copyProperties(page, result);
        result.setRecords(page.getRecords().stream().map(ObjectConvert::convert).collect(Collectors.toList()));
        return result;
    }

    /**
     * 教师图片信息处理
     *
     * @param course 教师类
     * @param cover  图片信息
     * @param choice 判断是新增还是修改
     */
    private void addCoverPath(EduTeacherDO course, MultipartFile cover, boolean choice) {
        try(InputStream in = cover.getInputStream()) {
            // 上传图片文件
            String url = minIoFileService.upload(
                    bucketName,
                    ImageUtil.getThumbnailContentType(cover),
                    coverPath,
                    course.getId() + cover.getOriginalFilename(),
                    in);
            if (choice) {
                save(course.setAvatar(url));
            } else {
                updateById(course.setAvatar(url));
            }
        } catch (IOException e) {
            throw new ServiceException(IMAGE_UPLOAD_FAIL);
        }
    }
}
