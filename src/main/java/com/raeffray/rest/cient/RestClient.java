package com.raeffray.rest.cient;

import java.text.MessageFormat;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.raeffray.commons.Configuration;
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

	public JSONObject executeBatchOperation(BatchOperationRequest request) throws Exception {
		
		String uri = GraphResourceCatalog.BATCH_OPERATION.getResource();
		String authKey = Configuration.getConfiguration().getString(
				"authorization.key");
		
		WebResource webResource = client.resource(graphUrl + uri);
		WebResource.Builder builder = webResource.accept(
				MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION,
				authKey);
		
		ClientResponse response = builder.post(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		
		return (JSONObject) new JSONParser().parse(response
				.getEntity(String.class));

	}

	public void fetchNodes(Integer... nodes) {

		BatchOperationRequest request = new BatchOperationRequest();

		for (int i = 0; i < nodes.length; i++) {
			Integer node = nodes[i];
			request.createOperation(0, GraphResourceCatalog.NODE_FETCH
					.getHttpMethod(), MessageFormat.format(
					GraphResourceCatalog.NODE_FETCH.getResource(), node), "");
		}
		
		//TODO call batch method above...
		// put this guy into the request..


	}

}