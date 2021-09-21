package com.education.service.base.config;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import com.education.service.base.entity.ResponseMsg;
import com.education.service.base.entity.ServiceException;
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

import static com.education.service.base.entity.enums.ResponseEnum.ERROR;

/**
 * @author Mr_W
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	public ResponseMsg internalErrorHandler(Exception e) {
		ResponseMsg resp;
		if (e instanceof ServiceException) {
			LOGGER.error("happened serviceException, Caused by " + getMessage(e), e);
			resp = ResponseMsg.resp(((ServiceException) e).getCode(), ((ServiceException) e).getMsg());
		} else {
			LOGGER.error("happened systemException, Caused by " + getMessage(e), e);
			resp = ResponseMsg.fail();
		}
		return resp;
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseMsg paramErrorHandler(MethodArgumentNotValidException e) {
		BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                LOGGER.error("invalid parameter, Caused by " + fieldError.getDefaultMessage(), e);
                return ResponseMsg.resp(ERROR.getCode(), fieldError.getDefaultMessage());
            }
        }
		return ResponseMsg.resp(ERROR);
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
	public ResponseMsg handlerMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		LOGGER.error(e.getParameterName() + "不能为空", e);
		return ResponseMsg.resp(ERROR, e.getParameterName() + "不能为空");
	}

	@ExceptionHandler(BindException.class)
	public ResponseMsg handlerBindException(BindException e) {
		LOGGER.error(e.getAllErrors().get(0).getDefaultMessage(), e);
		return ResponseMsg.resp(ERROR, e.getAllErrors().get(0).getDefaultMessage());
	}

	@ExceptionHandler(MultipartException.class)
	public ResponseMsg handleMultipartException(MultipartException e) {
		LOGGER.error("文件解析失败", e);
		return ResponseMsg.fail("文件解析失败");
	}

}
