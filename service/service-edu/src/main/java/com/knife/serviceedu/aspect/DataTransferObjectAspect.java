package com.knife.serviceedu.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Mr_W
 * @date 2021/2/18 9:22
 * @description: dto 参数切面
 */
@Aspect
@Component
public class DataTransferObjectAspect {

    /**
     * 切点
     */
    @Pointcut("execution(public * com.knife.serviceedu.web.*.*(..))")
    public void pointcut() {}

}
