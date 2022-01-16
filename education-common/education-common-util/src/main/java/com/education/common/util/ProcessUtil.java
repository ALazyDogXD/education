package com.education.common.util;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Mr_W
 * @date 2021/4/5 0:36
 * @description Process 工具类
 */
public class ProcessUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessUtil.class);

    private ProcessUtil() {}

    /**
     * 指令
     * @param dirPath 工作目录
     * @param cmd 指令
     * @return exit
     * @throws IOException 读写异常
     * @throws InterruptedException 线程中断
     */
    public static int process(String dirPath, LinkedList<String> cmd) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command(cmd);
        processBuilder.directory(new File(dirPath));
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        StringBuilder result = new StringBuilder();

        SequenceInputStream sis = new SequenceInputStream(process.getInputStream(), process.getErrorStream());
        // 设置编码格式并转换为输入流
        InputStreamReader inst = new InputStreamReader(sis, "GBK");
        // 输入流缓冲区
        BufferedReader reader = new BufferedReader(inst);
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("/n");
                LOGGER.info(Arrays.toString(cmd.toArray()).replace(",", "") + " --->: " + line);
            }
        } catch (IOException e) {
            LOGGER.warn("failed to read output from process", e);
        } finally {
            IOUtils.closeQuietly(reader);
        }
        process.waitFor();
        int exit = process.exitValue();
        process.destroy();
        if (exit != 0) {
            throw new IOException("failed to execute:" + processBuilder.command() + " with result:" + result);
        } else {
            return exit;
        }
    }

}
