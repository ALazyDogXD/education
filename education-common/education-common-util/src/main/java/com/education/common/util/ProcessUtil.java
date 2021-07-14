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

//    public static int process(LinkedList<String> cmd) throws IOException, InterruptedException {
//        ProcessBuilder builder = new ProcessBuilder();
//        builder.command(cmd);
//
//        LOGGER.info("开始执行");
//        Process process = builder.start();
//        // 取出输出流和错误流的信息
//        // 注意：必须要取出ffmpeg在执行命令过程中产生的输出信息，如果不取的话当输出流信息填满jvm存储输出留信息的缓冲区时，线程就回阻塞住
//        String print = print(process);
//        String cmdStr = Arrays.toString(cmd.toArray()).replace(",", "");
//
//        LOGGER.info("print: [{}]", print);
//        LOGGER.info("cmd: [{}]", cmdStr);
//
//        int exit = process.waitFor();
//        LOGGER.info("执行结束");
//        process.destroy();
//        // 等待 cmd 命令执行完
//        return exit;
//    }

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
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
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

//    private static String print(Process process) throws IOException, InterruptedException {
//        // 获取输出流并转换成缓冲区
//        BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
//        // 输出数据
//        bout.write("1 2");
//        bout.close();//关闭流
//
//        // SequenceInputStream是一个串联流，能够把两个流结合起来，通过该对象就可以将
//        // getInputStream方法和getErrorStream方法获取到的流一起进行查看了，当然也可以单独操作
//        SequenceInputStream sis = new SequenceInputStream(process.getInputStream(), process.getErrorStream());
//        // 设置编码格式并转换为输入流
//        InputStreamReader inst = new InputStreamReader(sis, "GBK");
//        // 输入流缓冲区
//        BufferedReader br = new BufferedReader(inst);
//
//        String res;
//        StringBuilder sb = new StringBuilder();
//        // 循环读取缓冲区中的数据
//        while ((res = br.readLine()) != null) {
//            LOGGER.info(res);
//            sb.append(res).append("\n");
//        }
//        br.close();
//        return sb.toString();
//    }

}
