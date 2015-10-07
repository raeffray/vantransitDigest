package com.raeffray.rest.cient;

import java.text.MessageFormat;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.raeffray.commons.Configuration;
import com.raeffray.graph.RelationshipDescriber;
import com.raeffray.json.JsonUtils;
import com.raeffray.raw.data.RawData;
import com.raeffray.raw.data.StopTimes;
import com.raeffray.raw.data.Trips;
import com.raeffray.rest.cient.enums.GraphResourceCatalog;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestClient {

	private static RestClient instance;

	private String graphUrl;

	static Logger logger = Logger.getLogger(RestClient.class);

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

		logger.info("posting!");
		ClientResponse response = builder.post(ClientResponse.class,
				request.parseJson());
		logger.info("posted!");

		if (response.getStatus() != 200) {
			logger.error("Fail");
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		logger.info("parsing the response");
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

	public long createNode(String[] labels, RawData rawData) throws Exception {
		logger.info("Creating request");
		BatchOperationRequest request = new BatchOperationRequest();
		int countIds = -1;
		int lastId = 0;
		String body = JsonUtils.parseJson(rawData);
		request.addOperation(++countIds,
				GraphResourceCatalog.BATCH_OPERATION_NODE_CREATE
						.getHttpMethod(),
				GraphResourceCatalog.BATCH_OPERATION_NODE_CREATE.getResource(),
				body);
		lastId = countIds;
		request.addOperation(++countIds,
				GraphResourceCatalog.BATCH_OPERATION_LABEL_CREATE
						.getHttpMethod(), MessageFormat.format(
						GraphResourceCatalog.BATCH_OPERATION_LABEL_CREATE
								.getResource(), "{" + lastId + "}"), JsonUtils
						.parseJsonSingleValue(labels));
		logger.info("request created");

		JSONArray executeBatchOperation = executeBatchOperation(request);

		return (Long) ((JSONObject) ((JSONObject) ((JSONObject) executeBatchOperation
				.get(0)).get("body")).get("metadata")).get("id");

	}

	public JSONArray createNodes(String[] labels, List<RawData> rawDataList)
			throws Exception {
		logger.info("Creating request");
		BatchOperationRequest request = new BatchOperationRequest();
		int countIds = -1;
		int lastId = 0;
		for (RawData rawData : rawDataList) {
			String body = JsonUtils.parseJson(rawData);
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
					JsonUtils.parseJsonSingleValue(labels));
		}
		logger.info("request created");
		return executeBatchOperation(request);
	}

	public JSONArray createNodeStructure(long fatherId,
			List<RawData> childNodes, String[] childLabels,
			RelationshipDescriber relationshipDescriber) throws Exception {

		String url = Configuration.getConfiguration().getString("graph.db.url");

		logger.info("Creating request");
		BatchOperationRequest request = new BatchOperationRequest();
		int countIds = 0;
		int lastId = 0;
		lastId = countIds;

		request.addOperation(
				countIds++,
				GraphResourceCatalog.BATCH_OPERATION_NODE_FETCH.getHttpMethod(),
				MessageFormat.format(
						GraphResourceCatalog.BATCH_OPERATION_NODE_FETCH
								.getResource(), String.valueOf(fatherId)), null);

		for (RawData rawData : childNodes) {
			definePersonalizaedRelAttributes(rawData, relationshipDescriber);
			String body = JsonUtils.parseJson(rawData);

			lastId = countIds;
			request.addOperation(countIds++,
					GraphResourceCatalog.BATCH_OPERATION_NODE_CREATE
							.getHttpMethod(),
					GraphResourceCatalog.BATCH_OPERATION_NODE_CREATE
							.getResource(), body);

			request.addOperation(countIds++,
					GraphResourceCatalog.BATCH_OPERATION_LABEL_CREATE
							.getHttpMethod(), MessageFormat.format(
							GraphResourceCatalog.BATCH_OPERATION_LABEL_CREATE
									.getResource(), "{" + lastId + "}"),
					JsonUtils.parseJsonSingleValue(childLabels));
			relationshipDescriber.setTo("{" + lastId + "}");

			request.addOperation(countIds++,

			GraphResourceCatalog.NODE_CREATE_RELATIONSHIP.getHttpMethod(),
					(url + MessageFormat.format(
							GraphResourceCatalog.NODE_CREATE_RELATIONSHIP
									.getResource(), String.valueOf(fatherId))),
					relationshipDescriber.parseJson());
		}
		logger.info("request created");
		JSONArray executeBatchOperation = executeBatchOperation(request);
		return executeBatchOperation;
	}

	public void definePersonalizaedRelAttributes(RawData rawData,
			RelationshipDescriber relationshipDescriber) {

		// if(rawData instanceof StopTimes){
		//
		// relationshipDescriber.addAttribute(key, value);
		//
		// }

	}
}
