package com.raeffray.csv;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.raeffray.commons.Configuration;
import com.raeffray.raw.data.RawData;
import com.raeffray.raw.instrospection.ReflectionData;

public class CSVReader {

	public List<Map<String, String>> readCSVForData(
			Class<? extends RawData> clazz) {

		Iterable<CSVRecord> records;

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		String csvPath = Configuration.getConfigurationForClass(clazz)
				.getString("csv.path");

		try {

			Reader in = new FileReader(csvPath);

			String[] fieldsNames = ReflectionData.getInstance()
					.extractFieldsNames(clazz);

			records = CSVFormat.EXCEL.withHeader(fieldsNames)
					.withSkipHeaderRecord(true)
					.withIgnoreSurroundingSpaces(true).parse(in);

			for (CSVRecord record : records) {
				Map<String, String> fields = new HashMap<String, String>();
				int fieldsNamesLength = fieldsNames.length;
				if (record.size() < fieldsNames.length) {
					--fieldsNamesLength;
				}
				for (int i = 0; i < fieldsNamesLength; i++) {
					String field = fieldsNames[i];
					String value = record.get(field).trim();
					fields.put(field, value);
				}
				list.add(fields);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
}
