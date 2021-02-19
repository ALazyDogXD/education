package com.knife.servicebase.config;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import com.knife.commonutil.util.ResponseBean;
import com.knife.servicebase.entity.ServiceException;
import com.knife.servicebase.enums.ServiceExceptionEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Mr_W
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	public ResponseBean internalErrorHandler(Exception e) {
		ResponseBean r;
		if (e instanceof ServiceException) {
			LOGGER.error("happened serviceException, Caused by " + getMessage(e), e);
			r = ResponseBean.fail(
					StringUtils.isBlank(((ServiceException) e).getAlertMessage()) ? e.getMessage() : ((ServiceException) e).getAlertMessage(),
					((ServiceException) e).getCode());
		} else {
			LOGGER.error("happened systemException, Caused by " + getMessage(e), e);
			r = ResponseBean.fail();
		}
		e.printStackTrace();
		return r;
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseBean paramErrorHandler(MethodArgumentNotValidException e) {
		BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                LOGGER.error("invalid parameter, Caused by " + fieldError.getDefaultMessage(), e);
                return ResponseBean.fail(fieldError.getDefaultMessage(), ServiceExceptionEnum.INVALID_PARAMS.getCode());
            }
        }
		return ResponseBean.fail(ServiceExceptionEnum.INVALID_PARAMS.getMsg(), ServiceExceptionEnum.INVALID_PARAMS.getCode());
	}
	
	private String getMessage(Exception e) {
		StringWriter sw = new StringWriter();
		try (PrintWriter pw = new PrintWriter(sw)) {
			e.printStackTrace(pw);
			pw.flush();
			sw.flush();
		}
		return sw.toString();
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseBean handlerMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		LOGGER.debug(e.getParameterName() + "不能为空", e);
		return ResponseBean.fail(e.getParameterName() + "不能为空");
	}

	@ExceptionHandler(BindException.class)
	public ResponseBean handlerBindException(BindException e) {
		LOGGER.debug(e.getAllErrors().get(0).getDefaultMessage(), e);
		return ResponseBean.fail(e.getAllErrors().get(0).getDefaultMessage());
	}

	@ExceptionHandler(MultipartException.class)
	public ResponseBean handleError1(MultipartException e) {
		LOGGER.error("文件解析失败", e);
		return ResponseBean.fail("文件解析失败");
	}

}
