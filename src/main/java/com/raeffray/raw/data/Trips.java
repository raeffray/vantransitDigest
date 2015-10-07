package com.raeffray.raw.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Trips implements RawData {
	
	@JsonIgnore
	private String route_id;
	
	@JsonIgnore
	private String service_id;
	
	@JsonProperty("tripId")
	private String trip_id;
	
	@JsonProperty("headsign")
	private String trip_headsign;
	
	@JsonProperty("shortName")
	private String trip_short_name;
	
	@JsonProperty("directionId")
	private String direction_id;
	
	@JsonProperty("blockId")
	private String block_id;
	
	@JsonIgnore
	private String shape_id;

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	public String getTrip_headsign() {
		return trip_headsign;
	}

	public void setTrip_headsign(String trip_headsign) {
		this.trip_headsign = trip_headsign;
	}

	public String getTrip_short_name() {
		return trip_short_name;
	}

	public void setTrip_short_name(String trip_short_name) {
		this.trip_short_name = trip_short_name;
	}

	public String getDirection_id() {
		return direction_id;
	}

	public void setDirection_id(String direction_id) {
		this.direction_id = direction_id;
	}

	public String getBlock_id() {
		return block_id;
	}

	public void setBlock_id(String block_id) {
		this.block_id = block_id;
	}

	public String getShape_id() {
		return shape_id;
	}

	public void setShape_id(String shape_id) {
		this.shape_id = shape_id;
	}
	
	

}
