package com.education.service.edu.web;

import com.education.service.base.entity.ResponseMsg;
import com.education.service.base.strategy.CreateDataTransferObject;
import com.education.service.edu.domain.dto.EduVideoDTO;
import com.education.service.edu.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Mr_W
 * @date 2021/3/27 19:11
 * @description 视频接口
 */
@Api(tags = "视频管理接口")
@RestController
@RequestMapping("video")
public class VideoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoController.class);

    @Resource
    private EduVideoService videoService;

    @PostMapping
    @ApiOperation("新增视频接口")
    public ResponseMsg create(@Validated(CreateDataTransferObject.class) EduVideoDTO video) {
        LOGGER.debug("视频入参: [{}]", video);
        videoService.uploadVideo(video.getVideo());
        return ResponseMsg.success("添加成功");
    }

    @GetMapping
    @ApiOperation("新增视频接口")
    public byte[] test(){
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(Paths.get("F:\\temp-temp\\my_video_manifest.mpd"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @GetMapping("{file}")
    @ApiOperation("新增视频接口")
    public byte[] test(@PathVariable("file") String file){
        System.out.println("-------------------------------------------------------------------------------" + file);
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(Paths.get("F:\\temp-temp\\" + file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

}
