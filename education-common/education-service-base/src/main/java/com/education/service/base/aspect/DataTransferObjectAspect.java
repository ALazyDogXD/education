package com.education.service.base.aspect;

import com.education.service.base.annotation.SetNull;
import com.education.service.base.entity.ServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

import static com.education.service.base.entity.enums.ResponseEnum.FAIL;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Mr_W
 * @date 2021/2/18 9:22
 * @description dto 参数切面
 */
@Aspect
@Component
@ConditionalOnClass(Aspect.class)
public class DataTransferObjectAspect {

    private static final Logger LOGGER = getLogger(DataTransferObjectAspect.class);

    @Pointcut("execution(public * com.*.*.*.web.*.*(..))")
    public void pointcut() {}

    @Around("pointcut()")
    public Object proceed(ProceedingJoinPoint p) throws Throwable {
        Instant now = Instant.now();
        MethodSignature signature = (MethodSignature) p.getSignature();
        Method method = signature.getMethod();
        Annotation[][] methodAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < methodAnnotations.length; i++) {
            for (Annotation annotation : methodAnnotations[i]) {
                if (annotation instanceof Validated) {
                    // 如果有分组
                    if (((Validated) annotation).value().length != 0) {
                        p.getArgs()[i] = buildParams(p.getArgs()[i], ((Validated) annotation).value()[0]);
                    }
                }
            }
        }
        LOGGER.debug("入参构建完毕, 用时: [{}] ms", Duration.between(now, Instant.now()).toMillis());
        return p.proceed();
    }

    /**
     * 构建入参
     * @param obj 原始入参
     * @return 构建后的入参
     */
    private Object buildParams(Object obj, Class<?> type) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation instanceof SetNull) {
                    try {
                        Method groupMethod = annotation.annotationType().getDeclaredMethod("group");
                        Class<?> group = (Class<?>) groupMethod.invoke(annotation);
                        if (type.isAssignableFrom(group)) {
                            field.setAccessible(true);
                            field.set(obj, null);
                            field.setAccessible(false);
                        }
                    } catch (NoSuchMethodException e) {
                        LOGGER.error("没有 groups 方法");
                        throw new ServiceException(FAIL, e);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        LOGGER.error("groups 方法执行失败");
                        throw new ServiceException(FAIL, e);
                    }
                }
            }
        }
        return obj;
    }

}
