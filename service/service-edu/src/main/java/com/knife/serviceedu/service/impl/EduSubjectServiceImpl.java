package com.knife.serviceedu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knife.commonutil.util.SpringBeanUtil;
import com.knife.servicebase.entity.ServiceException;
import com.knife.serviceedu.domain.entity.EduSubjectDO;
import com.knife.serviceedu.domain.entity.ExcelSubjectData;
import com.knife.serviceedu.listener.ImportSubjectListener;
import com.knife.serviceedu.mapper.EduSubjectMapper;
import com.knife.serviceedu.service.EduSubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Mr_W
 * @since 2021-02-16
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubjectDO> implements EduSubjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EduSubjectServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importSubjectFile(MultipartFile file) {
        if (Objects.isNull(file)) {
            throw new ServiceException("文件不能为空");
        }
        // 检测 file 是否合法
        if (checkFile(file)) {
            importExcelFile(file);
        } else {
            LOGGER.error("文件格式错误, ContentType: [{}], OriginalFilename: [{}]", file.getContentType(), file.getOriginalFilename());
            throw new ServiceException("文件格式错误");
        }
    }

    /**
     * 检测 file 是否合法
     * @param file 文件
     * @return true 合法
     */
    private boolean checkFile(MultipartFile file) {
        try {
            return !file.isEmpty() && (Objects.requireNonNull(file.getOriginalFilename()).endsWith(".xlsx") || Objects.requireNonNull(file.getOriginalFilename()).endsWith(".xls"));
        } catch (Exception e) {
            LOGGER.error("文件格式错误, ContentType: [{}], OriginalFilename: [{}]", file.getContentType(), file.getOriginalFilename(), e);
            throw new ServiceException("文件格式错误");
        }
    }

    /**
     * 导入 excel 文件
     * @param file excel 文件
     */
    private void importExcelFile(MultipartFile file) {
        try(InputStream in = file.getInputStream()) {
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
        } catch (IOException e) {
            LOGGER.error("数据导入失败", e);
            throw new ServiceException("文件错误, 数据导入失败");
        } catch (ExcelAnalysisException e) {
            LOGGER.error("excel 解析失败", e);
            throw new ServiceException(e.getMessage());
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
}
