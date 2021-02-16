package com.knife.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.knife.servicebase.entity.ServiceException;
import com.knife.serviceedu.domain.entity.EduSubjectDO;
import com.knife.serviceedu.domain.entity.ExcelSubjectData;
import com.knife.serviceedu.service.EduSubjectService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Mr_W
 * @date 2021/2/16 16:27
 * @description: 科目导入监听
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ImportSubjectListener extends AnalysisEventListener<ExcelSubjectData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportSubjectListener.class);

    @Resource
    private EduSubjectService eduSubjectService;

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        LOGGER.debug("类目数据: [{}]", excelSubjectData);
        if (StringUtils.isNotBlank(excelSubjectData.getLevelOneSubjectName())) {
            // 查询一级学科
            EduSubjectDO parentEduSubject = eduSubjectService.getByTitle(excelSubjectData.getLevelOneSubjectName());
            // 如果不存在, 新建一级学科
            if (Objects.isNull(parentEduSubject)) {
                int sort = eduSubjectService.getMaxSort() + 1;
                parentEduSubject = new EduSubjectDO() {{
                    setTitle(excelSubjectData.getLevelOneSubjectName());
                    setParentId("0");
                    setSort(sort);
                    setGmtCreate(LocalDateTime.now());
                    setGmtModified(LocalDateTime.now());
                }};
                eduSubjectService.save(parentEduSubject);
            }

            if (StringUtils.isNotBlank(excelSubjectData.getLevelTwoSubjectName())) {
                // 插入二级学科
                EduSubjectDO finalParentEduSubject = parentEduSubject;
                EduSubjectDO sonEduSubject = new EduSubjectDO() {{
                    setTitle(excelSubjectData.getLevelTwoSubjectName());
                    setParentId(finalParentEduSubject.getId());
                    setSort(finalParentEduSubject.getSort());
                    setGmtCreate(LocalDateTime.now());
                    setGmtModified(LocalDateTime.now());
                }};

                eduSubjectService.save(sonEduSubject);
            }


        } else if (StringUtils.isBlank(excelSubjectData.getLevelOneSubjectName()) &&
                StringUtils.isNotBlank(excelSubjectData.getLevelTwoSubjectName())) {
            throw new ServiceException("一级学科名称不能为空");
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        LOGGER.info("导入结束");
    }
}
