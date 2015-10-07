package com.raeffray.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.raeffray.raw.data.RawData;

public class JsonUtils {

	public static String parseJson(RawData data) throws Exception {
		ObjectWriter ow = new ObjectMapper().writer();
		return ow.writeValueAsString(data);
	}

	public static String parseJsonSingleValue(String[] values) throws Exception {
		ObjectWriter ow = new ObjectMapper().writer();
		return ow.writeValueAsString(values);
	}

}
