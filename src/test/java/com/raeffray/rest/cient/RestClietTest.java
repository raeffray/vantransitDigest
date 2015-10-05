package com.raeffray.rest.cient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.text.MessageFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import com.raeffray.rest.cient.enums.GraphResourceCatalog;

public class RestClietTest {

	// @Test
	public void nodeFetchTest() throws Exception {
		JSONObject fetchNodeById = RestClient.getInstance().fetchNodeById(41);
		fetchNodeById.get("data");
	}

	@Test
	public void createBatchRequestTest() throws Exception {
		BatchOperationRequest request = new BatchOperationRequest();
		request.addOperation(0, GraphResourceCatalog.BATCH_OPERATION_NODE_FETCH
				.getHttpMethod(), MessageFormat.format(
				GraphResourceCatalog.BATCH_OPERATION_NODE_FETCH.getResource(),
				40), "aaa");

		assertThat(
				request.parseJson(),
				is(equalTo("[{\"id\":0,\"method\":\"GET\",\"to\":\"/node/40\",\"body\":\"aaa\"}]")));
	}

	@Test
	public void createBatchRequestWithNullBodyTest() throws Exception {
		BatchOperationRequest request = new BatchOperationRequest();
		request.addOperation(0, GraphResourceCatalog.BATCH_OPERATION_NODE_FETCH
				.getHttpMethod(), MessageFormat.format(
				GraphResourceCatalog.BATCH_OPERATION_NODE_FETCH.getResource(),
				40), null);
		assertThat(
				request.parseJson(),
				is(equalTo("[{\"id\":0,\"method\":\"GET\",\"to\":\"/node/40\"}]")));
	}

	//@Test
	public void batchFetchNodeTest() throws Exception {
		JSONArray node = RestClient.getInstance().fetchNodes(40);
		Object object = node.get(0);
		String value = ((JSONObject) ((JSONObject) object).get("body")).get(
				"data").toString();
		assertThat(value,
				is(equalTo("{\"name\":\"Renato\",\"surname\":\"Barbosa\"}")));
	}
	
	//@Test
	public void batchFetchMultipleNodeTest() throws Exception {
		JSONArray node = RestClient.getInstance().fetchNodes(40,41);
		Object object = node.get(0);
		String value = ((JSONObject) ((JSONObject) object).get("body")).get(
				"data").toString();
		assertThat(value,
				is(equalTo("{\"name\":\"Renato\",\"surname\":\"Barbosa\"}")));
		object = node.get(1);
		value = ((JSONObject) ((JSONObject) object).get("body")).get(
				"data").toString();
		assertThat(value,
				is(equalTo("{\"name\":\"Traci\",\"surname\":\"Miles\"}")));
	
	}
	
	

}
