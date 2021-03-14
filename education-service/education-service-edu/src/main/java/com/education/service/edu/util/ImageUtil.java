package com.education.service.edu.util;

import com.education.service.base.entity.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Mr_W
 * @date 2021/3/13 22:06
 * @description 图片工具
 */
public class ImageUtil {

    /**
     * 图片文件大小
     */
    private static final long M2_TO_BYTE = (1 << 20) * 2;

    private ImageUtil() { }

    /**
     * 获取图片文件的媒体格式
     *
     * @param thumbnail 缩略图
     * @return 媒体格式
     */
    public static String getThumbnailContentType(MultipartFile thumbnail) throws IOException {
        //检查文件是否为空
        if (thumbnail.isEmpty()) {
            throw ServiceException.serviceException("图片为空").alertMessage("请选择要上传的图片").build();
        }
        //检查文件大小
        if (thumbnail.getSize() > M2_TO_BYTE) {
            throw ServiceException.serviceException("图片大小超范围").alertMessage("图片文件不得大于 2 MB").build();
        }
        //检查是否是图片
        if (Objects.isNull(ImageIO.read(thumbnail.getInputStream()))) {
            throw ServiceException.serviceException("文件类型错误").alertMessage("文件类型错误").build();
        }
        String suffix = Objects.requireNonNull(thumbnail.getOriginalFilename()).substring(thumbnail.getOriginalFilename().lastIndexOf(".") + 1);
        return "image/" + ("jpg".equals(suffix) || "jfif".equals(suffix) ? "jpeg" : suffix);
    }

}
