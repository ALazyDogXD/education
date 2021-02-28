package com.education.service.base.entity;

import java.lang.reflect.ParameterizedType;

import org.springframework.beans.BeanUtils;

/**
 * @author Mr_W
 * 对象转换
 */
public class ObjectConvert<T> {
	
	protected void beforeConvert() {}
	 
	protected void afterConvert(T t) {}
	
	public T convert() {
		T t;
		beforeConvert();
		try {
			t = newInstance();
			BeanUtils.copyProperties(this, t);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Bean convert failed, Caused by " + e);
		}
		afterConvert(t);
		return t;
	}
	
	@SuppressWarnings("unchecked")
	private T newInstance() throws Exception {
		Class<T> clazz = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz.newInstance();
	}

}
