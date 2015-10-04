package com.raeffray.rest.cient;

import java.text.MessageFormat;
import java.util.Collection;

import org.apache.commons.lang.WordUtils;
import org.json.simple.JSONObject;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.raeffray.rest.cient.BatchOperationRequest.Operation;
import com.raeffray.rest.cient.enums.GraphResourceCatalog;

public class RestClietTest {

	// @Test
	public void nodeFetchTest() throws Exception {
		JSONObject fetchNodeById = RestClient.getInstance().fetchNodeById(41);
		fetchNodeById.get("data");
	}

	@Test
	public void createOperationTest() throws Exception {

		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();

		BatchOperationRequest request = new BatchOperationRequest();

		String body = "";

		request.createOperation(
				0,
				GraphResourceCatalog.NODE_FETCH.getHttpMethod(),
				MessageFormat.format(
						GraphResourceCatalog.NODE_FETCH.getResource(), 40), "");

		System.out.println(request.parseJson());

		int a = 1;

	}

}
