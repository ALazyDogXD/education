package com.education.service.vod.util;

import com.education.common.util.ProcessUtil;
import com.education.service.base.entity.ServiceException;
import com.education.service.base.entity.enums.ResponseEnum;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Mr_W
 * @date 2021/4/4 15:47
 * @description FFmpeg 工具
 */
public class FfmpegUtil {

    private FfmpegUtil() {
    }

    /**
     * 将视频文件转换为 mpd 文件
     *
     * @param path 视频路径
     * @return mpd 文件路径
     */
    public static String convertVideoIntoMpd(String path) {
        String workDir = path.substring(0, path.lastIndexOf(File.separator));
        String fileNameWithoutSuffix = path.substring(path.lastIndexOf(File.separator) + File.separator.length(), path.lastIndexOf("."));

        // 视频转码
        LinkedList<String> convertIntoMp4Cmd = new LinkedList<>();
        convertIntoMp4Cmd.add("ffmpeg");
        convertIntoMp4Cmd.add("-i");
        convertIntoMp4Cmd.add(path);
        convertIntoMp4Cmd.add(workDir + File.separator + fileNameWithoutSuffix + "-temp.mp4");

        LinkedList<String> convertTempIntoH265Mp4Cmd = new LinkedList<>();
        convertTempIntoH265Mp4Cmd.add("ffmpeg");
        convertTempIntoH265Mp4Cmd.add("-i");
        convertTempIntoH265Mp4Cmd.add(workDir + File.separator + fileNameWithoutSuffix + "-temp.mp4");
        convertTempIntoH265Mp4Cmd.add("-c:v");
        convertTempIntoH265Mp4Cmd.add("libx265");
        convertTempIntoH265Mp4Cmd.add("-tag:v");
        convertTempIntoH265Mp4Cmd.add("hvc1");
        convertTempIntoH265Mp4Cmd.add(workDir + File.separator + fileNameWithoutSuffix + "-mp4.mp4");

        LinkedList<String> convertIntoH265Mp4Cmd = new LinkedList<>();
        convertIntoH265Mp4Cmd.add("ffmpeg");
        convertIntoH265Mp4Cmd.add("-i");
        convertIntoH265Mp4Cmd.add(workDir + File.separator + fileNameWithoutSuffix + ".mp4");
        convertIntoH265Mp4Cmd.add("-c:v");
        convertIntoH265Mp4Cmd.add("libx265");
        convertIntoH265Mp4Cmd.add("-tag:v");
        convertIntoH265Mp4Cmd.add("hvc1");
        convertIntoH265Mp4Cmd.add(workDir + File.separator + fileNameWithoutSuffix + "-mp4.mp4");

        LinkedList<String> convertIntoMpdCmd = new LinkedList<>();
        convertIntoMpdCmd.add("ffmpeg");
        convertIntoMpdCmd.add("-i");
        convertIntoMpdCmd.add(workDir + File.separator + fileNameWithoutSuffix + "-mp4.mp4");
        convertIntoMpdCmd.add("-c");
        convertIntoMpdCmd.add("copy");
        convertIntoMpdCmd.add("-f");
        convertIntoMpdCmd.add("dash");
        convertIntoMpdCmd.add("-init_seg_name");
        convertIntoMpdCmd.add("init-" + fileNameWithoutSuffix + "$RepresentationID$.$ext$");
        convertIntoMpdCmd.add("-media_seg_name");
        convertIntoMpdCmd.add("chunk-" + fileNameWithoutSuffix + "$RepresentationID$-$Number%05d$.$ext$");
        convertIntoMpdCmd.add(workDir + File.separator + fileNameWithoutSuffix + ".mpd");
        try {
            if (!path.endsWith(".mp4")) {
                ProcessUtil.process(workDir, convertIntoMp4Cmd);
                ProcessUtil.process(workDir, convertTempIntoH265Mp4Cmd);
            } else {
                ProcessUtil.process(workDir, convertIntoH265Mp4Cmd);
            }
            ProcessUtil.process(workDir, convertIntoMpdCmd);
        } catch (IOException | InterruptedException e) {
            throw new ServiceException(ResponseEnum.VIDEO_UPLOAD_FAIL, "转码失败");
        }

        return workDir + File.separator + fileNameWithoutSuffix + ".mpd";
    }

}
