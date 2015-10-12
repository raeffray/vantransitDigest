package com.raeffray.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.raeffray.raw.data.RawData;

public class JsonUtils {

	public static String generateJson(RawData data) {
		try {
			ObjectWriter ow = new ObjectMapper().writer();
			return ow.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String generateJsonSingleValue(String[] values) {
		try {
			ObjectWriter ow = new ObjectMapper().writer();
			return ow.writeValueAsString(values);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
