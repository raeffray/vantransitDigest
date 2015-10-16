package com.raeffray.raw.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raeffray.annotations.RawDataId;

@JsonInclude(Include.NON_NULL)
@RawDataId(identifier="agency_id")
public class Agency implements RawData {
	
	@JsonProperty("agencyId")
	private String agency_id; 
	
	@JsonProperty("name")
	private String agency_name;
	
	@JsonProperty("url")
	private String agency_url;
	
	@JsonProperty("timeZone")
	private String agency_timezone; 
	
	@JsonProperty("locale")
	private String agency_lang;

	public String getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(String agency_id) {
		this.agency_id = agency_id;
	}

	public String getAgency_name() {
		return agency_name;
	}

	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}

	public String getAgency_url() {
		return agency_url;
	}

	public void setAgency_url(String agency_url) {
		this.agency_url = agency_url;
	}

	public String getAgency_timezone() {
		return agency_timezone;
	}

	public void setAgency_timezone(String agency_timezone) {
		this.agency_timezone = agency_timezone;
	}

	public String getAgency_lang() {
		return agency_lang;
	}

	public void setAgency_lang(String agency_lang) {
		this.agency_lang = agency_lang;
	}
	
	
}
