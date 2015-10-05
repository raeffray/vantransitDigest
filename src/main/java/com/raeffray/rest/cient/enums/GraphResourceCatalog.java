package com.raeffray.rest.cient.enums;


public enum GraphResourceCatalog {
	
	NODE_CREATE ("/db/data/node", "POST"),
	NODE_FETCH ("/db/data/node/{0}","GET"),
	NODE_CREATE_RELATIONSHIP ("/db/data/node/{0}/relationships", "POST"),
	BATCH_OPERATION_NODE_FETCH("/node/{0}", "GET"),
	BATCH_OPERATION("/db/data/batch", "POST");
	
	private final String resource;
	
	private final String httpMethod;
		
	GraphResourceCatalog(String resource, String httpMethod){
		this.resource = resource;
		this.httpMethod = httpMethod;
	}

	public String getResource() {
		return resource;
	}

	public String getHttpMethod() {
		return httpMethod;
	}
}
