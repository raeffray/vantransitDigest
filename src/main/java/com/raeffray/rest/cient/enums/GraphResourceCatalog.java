package com.raeffray.rest.cient.enums;


public enum GraphResourceCatalog {
	
	NODE_CREATE ("/db/data/node"),
	NODE_FETCH ("/db/data/node/{0}"),
	NODE_CREATE_RELATIONSHIP ("/db/data/node/{0}/relationships");
	
	private final String resource;
		
	GraphResourceCatalog(String resource){
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
