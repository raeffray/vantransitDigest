package com.raeffray.raw.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a Bus Route
 *  
 *  @author Renato Barbosa
 *  
 * */

public class Routes implements RawData{
	
	@JsonProperty("routeId")
	private String route_id;
	
	@JsonIgnore
	// ids as foreign keys will be ignored
	private String agency_id;

	@JsonProperty("code")
	private String route_short_name;
	
	@JsonProperty("longName")
	private String route_long_name;
	
	@JsonProperty("description")
	private String route_desc;
	
	@JsonProperty("type")
	private String route_type;
	
	@JsonProperty("url")
	private String route_url;
	
	@JsonIgnore
	private String route_color;
	
	@JsonIgnore
	private String route_text_color;

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	public String getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(String agency_id) {
		this.agency_id = agency_id;
	}

	public String getRoute_short_name() {
		return route_short_name;
	}

	public void setRoute_short_name(String route_short_name) {
		this.route_short_name = route_short_name;
	}

	public String getRoute_long_name() {
		return route_long_name;
	}

	public void setRoute_long_name(String route_long_name) {
		this.route_long_name = route_long_name;
	}

	public String getRoute_desc() {
		return route_desc;
	}

	public void setRoute_desc(String route_desc) {
		this.route_desc = route_desc;
	}

	public String getRoute_type() {
		return route_type;
	}

	public void setRoute_type(String route_type) {
		this.route_type = route_type;
	}

	public String getRoute_url() {
		return route_url;
	}

	public void setRoute_url(String route_url) {
		this.route_url = route_url;
	}

	public String getRoute_color() {
		return route_color;
	}

	public void setRoute_color(String route_color) {
		this.route_color = route_color;
	}

	public String getRoute_text_color() {
		return route_text_color;
	}

	public void setRoute_text_color(String route_text_color) {
		this.route_text_color = route_text_color;
	}
	
	
}
