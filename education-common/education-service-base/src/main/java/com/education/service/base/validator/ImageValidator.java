package com.education.service.base.validator;

import com.education.service.base.annotation.validate.Image;
import com.education.service.base.entity.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author Mr_W
 * @date 2021/4/10 14:22
 * @description 图片检验
 */
public class ImageValidator implements ConstraintValidator<Image, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        //检查是否是图片
        try (InputStream in = value.getInputStream();) {
            if (Objects.isNull(ImageIO.read(in))) {
                return false;
            }
        } catch (IOException e) {
            throw ServiceException.serviceException("文件读取失败").alertMessage("文件错误").build();
        }
        return true;
    }


}
