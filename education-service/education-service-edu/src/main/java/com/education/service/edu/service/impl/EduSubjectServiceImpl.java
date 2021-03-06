package com.education.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.education.common.util.SpringBeanUtil;
import com.education.service.base.entity.ServiceException;
import com.education.service.edu.domain.entity.EduSubjectDO;
import com.education.service.edu.domain.entity.ExcelSubjectData;
import com.education.service.edu.domain.vo.EduSubjectParentVO;
import com.education.service.edu.domain.vo.EduSubjectVO;
import com.education.service.edu.listener.ImportSubjectListener;
import com.education.service.edu.mapper.EduSubjectMapper;
import com.education.service.edu.service.EduSubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import static com.education.service.base.entity.enums.ResponseEnum.*;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubjectDO> implements EduSubjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EduSubjectServiceImpl.class);

    @Override
    public void importSubjectFile(MultipartFile file) {
        if (Objects.isNull(file)) {
            throw new ServiceException(EXCEL_IS_NULL);
        }
        // 检测 file 是否合法
        if (checkFile(file)) {
            importExcelFile(file);
        } else {
            throw new ServiceException(IS_NOT_EXCEL);
        }
    }

    /**
     * 检测 file 是否合法
     *
     * @param file 文件
     * @return true 合法
     */
    private boolean checkFile(MultipartFile file) {
        try {
            return !file.isEmpty() && (Objects.requireNonNull(file.getOriginalFilename()).endsWith(".xlsx") || Objects.requireNonNull(file.getOriginalFilename()).endsWith(".xls"));
        } catch (Exception e) {
            throw new ServiceException(EXCEL_UPLOAD_FAIL, e);
        }
    }

    /**
     * 导入 excel 文件
     *
     * @param file excel 文件
     */
    private void importExcelFile(MultipartFile file) {
        try (InputStream in = file.getInputStream()) {
            ExcelReader excelReader = EasyExcel.read(in, ExcelSubjectData.class, SpringBeanUtil.getBean(ImportSubjectListener.class)).build();
            // 获取表单
            List<ReadSheet> readSheets = excelReader.excelExecutor().sheetList();
            LOGGER.info("开始导入 excel, 表单数: [{}]", readSheets.size());
            // 遍历表单并导入
            for (ReadSheet sheet : readSheets) {
                LOGGER.info("开始导入第 [{}] 张表单", sheet.getSheetNo() + 1);
                sheet.setHeadRowNumber(0);
                excelReader.read(sheet);
            }
        } catch (IOException | ExcelAnalysisException e) {
            throw new ServiceException(EXCEL_UPLOAD_FAIL, e);
        }
    }

    @Override
    public EduSubjectDO getByTitle(String title) {
        return baseMapper.selectOne(new LambdaQueryWrapper<EduSubjectDO>().eq(EduSubjectDO::getTitle, title));
    }

    @Override
    public int getMaxSort() {
        return baseMapper.getMaxSort();
    }

    @Override
    public int getLevelTwoCount(String id) {
        return baseMapper.selectCount(new LambdaQueryWrapper<EduSubjectDO>().eq(EduSubjectDO::getParentId, id));
    }

    @Override
    public List<EduSubjectParentVO> getTree() {
        List<EduSubjectDO> allSubjects = list();
        return getTree(allSubjects);
    }

    /**
     * 构建树
     *
     * @param subjects 科目集合
     * @return 构建树
     */
    private List<EduSubjectParentVO> getTree(List<EduSubjectDO> subjects) {
        List<EduSubjectParentVO> subjectParents = new ArrayList<>(20);
        ListIterator<EduSubjectDO> iterator = subjects.listIterator();
        // 查询父节点
        while (iterator.hasNext()) {
            EduSubjectDO subject = iterator.next();
            if ("0".equals(subject.getParentId())) {
                // 节点排序
                int index = getSubjectSite(subjectParents, subject);
                subjectParents.add(index, new EduSubjectParentVO() {{
                    setId(subject.getId());
                    setSort(subject.getSort());
                    setTitle(subject.getTitle());
                }});
                iterator.remove();
            }
        }
        // 匹配子节点
        subjects.forEach(subject -> subjectParents.forEach(pSubject -> {
            if (pSubject.getId().equals(subject.getParentId())) {
                int index = getSubjectSite(pSubject.getSubjects(), subject);
                pSubject.getSubjects().add(index, subject.convert());
            }
        }));
        return subjectParents;
    }

    /**
     * 获取科目的顺序位置
     *
     * @param subjects 科目集合
     * @param subject  科目
     * @return 位置
     */
    private int getSubjectSite(List<? extends EduSubjectVO> subjects, EduSubjectDO subject) {
        for (int i = subjects.size() - 1; i >= 0; i--) {
            if (subject.getSort() >= subjects.get(i).getSort()) {
                return i + 1;
            }
        }
        return 0;
    }
}
