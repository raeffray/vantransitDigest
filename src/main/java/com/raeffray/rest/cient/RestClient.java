package com.raeffray.rest.cient;

import com.raeffray.commons.Configuration;
import com.raeffray.graph.RelationshipDescriber;
import com.raeffray.json.JsonUtils;
import com.raeffray.raw.data.RawData;
import com.raeffray.raw.data.Trips;
import com.raeffray.rest.cient.enums.GraphResourceCatalog;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

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

	public JSONArray executeBatchOperation(BatchOperationRequest request) {

		try {
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
				logger.error("request: [" + request.parseJson() + "]");
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			logger.info("parsing the response");
			return (JSONArray) new JSONParser().parse(response
					.getEntity(String.class));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

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

	public long createNode(String[] labels, RawData... rawData) {
		logger.info("Creating request");
		BatchOperationRequest request = new BatchOperationRequest();
		int countIds = -1;
		int lastId = 0;
		for (RawData rawDataItem : rawData) {
			String body = JsonUtils.generateJson(rawDataItem);
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
							.generateJsonSingleValue(labels));
		}
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
			String body = JsonUtils.generateJson(rawData);
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
					JsonUtils.generateJsonSingleValue(labels));
		}
		logger.info("request created");
		return executeBatchOperation(request);
	}

	public JSONArray createNodeStructureForTrip(List<RawData> childNodes,
			String[] childLabels, RelationshipDescriber relationshipDescriber,
			RelationshipDescriber serviceRelationshipDescriber,
			Map<String, Long> nodeServiceIds, long fatherId) throws Exception {

		String url = Configuration.getConfiguration().getString("graph.db.url");

		logger.info("Creating request");
		BatchOperationRequest request = new BatchOperationRequest();
		int countIds = 0;
		int lastId = 0;
		lastId = countIds;

		for (RawData rawData : childNodes) {
			Trips trip = (Trips) rawData;

			Long serviceNodeId = nodeServiceIds.get(trip.getService_id());

			definePersonalizaedRelAttributes(rawData, relationshipDescriber);
			String body = JsonUtils.generateJson(rawData);

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
					JsonUtils.generateJsonSingleValue(childLabels));
			relationshipDescriber.setTo("{" + lastId + "}");
			serviceRelationshipDescriber.setTo((url + MessageFormat.format(
					GraphResourceCatalog.NODE_FETCH.getResource(),
					String.valueOf(serviceNodeId))));

			request.addOperation(countIds++,
					GraphResourceCatalog.NODE_CREATE_RELATIONSHIP
							.getHttpMethod(), (url + MessageFormat.format(
							GraphResourceCatalog.NODE_CREATE_RELATIONSHIP
									.getResource(), String.valueOf(fatherId))),
					relationshipDescriber.parseJson());

			request.addOperation(
					countIds++,
					GraphResourceCatalog.BATCH_OPERATION_RELATIONSHIP_CREATE
							.getHttpMethod(),
					MessageFormat
							.format(GraphResourceCatalog.BATCH_OPERATION_RELATIONSHIP_CREATE
									.getResource(), "{" + lastId + "}"),
					serviceRelationshipDescriber.parseJson());
		}
		logger.info("request created");
		JSONArray executeBatchOperation = executeBatchOperation(request);
		return executeBatchOperation;
	}

	public JSONArray createNodeStructure(List<RawData> childNodes,
			String[] childLabels, RelationshipDescriber relationshipDescriber,
			long... fatherIds) throws Exception {

		String url = Configuration.getConfiguration().getString("graph.db.url");

		logger.info("Creating request");
		BatchOperationRequest request = new BatchOperationRequest();
		int countIds = 0;
		int lastId = 0;
		lastId = countIds;

		for (RawData rawData : childNodes) {
			definePersonalizaedRelAttributes(rawData, relationshipDescriber);
			String body = JsonUtils.generateJson(rawData);

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
					JsonUtils.generateJsonSingleValue(childLabels));
			relationshipDescriber.setTo("{" + lastId + "}");

			for (long fatherId : fatherIds) {
				request.addOperation(countIds++,
						GraphResourceCatalog.NODE_CREATE_RELATIONSHIP
								.getHttpMethod(), (url + MessageFormat.format(
								GraphResourceCatalog.NODE_CREATE_RELATIONSHIP
										.getResource(), String
										.valueOf(fatherId))),
						relationshipDescriber.parseJson());
			}
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
