package com.shopease.utils;

import java.lang.reflect.Method;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtils {
	
	public static void setValues(Object obj, String[] propertyNames, Object[] values) {
		
		Map<String, Object> propertyMap = new HashMap<>();
		
		for(int i=0; i<propertyNames.length; i++) {
			propertyMap.put(propertyNames[i], values[i]);
		}
		
		Class<?> clazz = obj.getClass();
		Method[] methods = clazz.getMethods();
		
		for(Method m: methods) {
			if(isSetter(m)) {
				String propertyName = m.getName().substring(3);
				propertyName = Character.toLowerCase(propertyName.charAt(0))+propertyName.substring(1);
				try {
					Object value = propertyMap.get(propertyName);
					m.invoke(obj, convertValue(value, m.getParameterTypes()[0]));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static Object convertValue(Object value, Class<?> targetType) {
		if(value == null || targetType.isInstance(value)) {
			return value;
		}
		
		try {
			if(targetType == int.class || targetType == Integer.class) {
				return Integer.valueOf(value.toString());
			}else if(targetType == long.class || targetType == Long.class) {
				return Long.valueOf(value.toString());
			}else if(targetType == double.class || targetType == Double.class) {
				return Double.valueOf(value.toString());
			}else if(targetType == boolean.class || targetType == Boolean.class) {
				return Boolean.valueOf(value.toString());
			}else if(targetType == Date.class && value instanceof String) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.parseObject(value.toString());
			}else if(targetType == String.class) {
				return value.toString();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException("Unsupported target type "+targetType.getName());
	}

	private static boolean isSetter(Method m) {
		return m.getName().startsWith("set") && m.getParameterCount() == 1;
	}

}
