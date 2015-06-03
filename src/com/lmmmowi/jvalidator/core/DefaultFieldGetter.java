package com.lmmmowi.jvalidator.core;

import java.lang.reflect.Field;

public class DefaultFieldGetter implements IFiledGetter {

	@Override
	public Object getField(Object obj, String key) {
		try {
			Object value = null;
			Field field = obj.getClass().getDeclaredField(key);
			field.setAccessible(true);
			value = field.get(obj);
			field.setAccessible(false);
			return value;
		} catch (Exception e) {
			return null;
		}
	}

}
