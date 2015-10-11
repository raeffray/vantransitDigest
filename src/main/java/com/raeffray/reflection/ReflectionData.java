package com.raeffray.reflection;

import com.raeffray.raw.data.RawData;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class to handle reflection jobs
 * 
 * @author Renato Barbosa
 * 
 * */
public class ReflectionData {

	private static ReflectionData instance = null;

	static Logger logger = Logger.getLogger(ReflectionData.class);

	protected ReflectionData() {

	}

	public static ReflectionData getInstance() {
		if (instance == null) {
			instance = new ReflectionData();
		}
		return instance;
	}

	@SuppressWarnings("rawtypes")
	public String[] extractFieldsNames(Class<? extends RawData> clazz) {

		List<String> fields = new ArrayList<String>();
		Field[] allFields = FieldUtils.getAllFields(clazz);

		String[] fieldsName = new String[allFields.length];

		for (int i = 0; i < allFields.length; i++) {
			fieldsName[i] = allFields[i].getName();
		}
		return fieldsName;
	}

	public <T> List<T> buildList(Class<?> clazz,
			List<Map<String, String>> entries) throws Exception {

		List<T> list = new ArrayList<>();

		for (Map<String, String> fields : entries) {

			T object = buildInstance(clazz, fields);

			list.add(object);
		}

		return list;
	}

	public <T> T buildInstance(Class<?> clazz, Map<String, String> fields) throws InstantiationException, IllegalAccessException, InvocationTargetException {
		Set<String> keySet = fields.keySet();

		T object = (T) clazz.newInstance();

		for (String field : keySet) {

			String value = fields.get(field);

			Method publicMethod;
			try {
				publicMethod = clazz.getMethod("set" + WordUtils.capitalize(field), String.class);
				publicMethod.invoke(object, value);
			} catch (NoSuchMethodException e) {
				// Ignore unmapped fields
			}
		}
		return object;
	}
}
