package com.raeffray.rest.cient;

import java.text.MessageFormat;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.raeffray.commons.Configuration;
import com.raeffray.raw.data.RawData;
import com.raeffray.rest.cient.enums.GraphResourceCatalog;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestClient {

	private static RestClient instance;

	private String graphUrl;

	Client client;

	private RestClient() {
		client = Client.create();
		graphUrl = Configuration.getConfiguration().getString("graph.db.url");
	}

	public static RestClient getInstance() {
		if (instance == null) {
			instance = new RestClient();
			return instance;
		}
		return instance;
	}

	public JSONObject fetchNodeById(int nodeId) throws Exception {

		String uri = MessageFormat.format(
				GraphResourceCatalog.NODE_FETCH.getResource(), nodeId);
		String authKey = Configuration.getConfiguration().getString(
				"authorization.key");

		WebResource webResource = client.resource(graphUrl + uri);
		WebResource.Builder builder = webResource.accept(
				MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION,
				authKey);

		ClientResponse response = builder.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		return (JSONObject) new JSONParser().parse(response
				.getEntity(String.class));

	}

	public JSONArray executeBatchOperation(BatchOperationRequest request)
			throws Exception {

		String uri = GraphResourceCatalog.BATCH_OPERATION.getResource();
		String authKey = Configuration.getConfiguration().getString(
				"authorization.key");

		WebResource webResource = client.resource(graphUrl + uri);

		WebResource.Builder builder = webResource.accept(
				MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION,
				authKey);

		ClientResponse response = builder.post(ClientResponse.class,
				request.parseJson());

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		return (JSONArray) new JSONParser().parse(response
				.getEntity(String.class));

	}

	public JSONArray fetchNodes(Integer... nodes) throws Exception {

		BatchOperationRequest request = new BatchOperationRequest();

		for (int i = 0; i < nodes.length; i++) {
			Integer node = nodes[i];
			request.addOperation(0,
					GraphResourceCatalog.BATCH_OPERATION_NODE_FETCH
							.getHttpMethod(), MessageFormat.format(
							GraphResourceCatalog.BATCH_OPERATION_NODE_FETCH
									.getResource(), node), null);
		}

		return executeBatchOperation(request);
	}

	public JSONArray createNode(String[] labels, RawData... rawDataList)
			throws Exception {

		BatchOperationRequest request = new BatchOperationRequest();

		int countIds = -1;
		int lastId = 0;

		for (RawData rawData : rawDataList) {

			String body = parseJson(rawData);
			request.addOperation(++countIds,
					GraphResourceCatalog.BATCH_OPERATION_NODE_CREATE
							.getHttpMethod(),
					GraphResourceCatalog.BATCH_OPERATION_NODE_CREATE
							.getResource(), body);
			lastId = countIds;
			request.addOperation(++countIds,
					GraphResourceCatalog.BATCH_OPERATION_LABEL_CREATE
							.getHttpMethod(), MessageFormat.format(
							GraphResourceCatalog.BATCH_OPERATION_LABEL_CREATE
									.getResource(), "{" + lastId + "}"),
					parseJsonSingleValue(labels));
		}

		return executeBatchOperation(request);
	}

	private String parseJson(RawData data) throws Exception {
		ObjectWriter ow = new ObjectMapper().writer();
		return ow.writeValueAsString(data);
	}

	private String parseJsonSingleValue(String[] values) throws Exception {
		ObjectWriter ow = new ObjectMapper().writer();
		return ow.writeValueAsString(values);
	}

}
