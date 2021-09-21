package com.education.service.base.entity;

import java.lang.reflect.ParameterizedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * @author Mr_W
 * 对象转换
 */
public class ObjectConvert<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectConvert.class);
	
	protected void beforeConvert() {}

	protected void beforeConvert(T t) {}
	 
	protected void afterConvert(T t) {}
	
	public T convert() {
		T t;
		beforeConvert();
		try {
			t = newInstance();
			BeanUtils.copyProperties(this, t);
			LOGGER.debug("source: {} -> target :{}", this, t);
		} catch (Exception e) {
			throw new RuntimeException("Bean convert failed, Caused by " + e);
		}
		afterConvert(t);
		return t;
	}

	public Object convert(T t) {
		beforeConvert(t);
		try {
			BeanUtils.copyProperties(t, this);
			LOGGER.debug("source: {} -> target :{}", t, this);
		} catch (Exception e) {
			throw new RuntimeException("Bean convert failed, Caused by " + e);
		}
		afterConvert(t);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	private T newInstance() throws Exception {
		Class<T> clazz = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz.newInstance();
	}

}
