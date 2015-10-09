package com.raeffray.raw.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;

public class Calendar implements RawData {

	@JsonProperty("serviceId")
	private String service_id;
	
	@JsonRawValue
	private String monday;
	
	@JsonRawValue
	private String tuesday;
	
	@JsonRawValue
	private String wednesday;
	
	@JsonRawValue
	private String thursday;
	
	@JsonRawValue
	private String friday;
	
	@JsonRawValue
	private String saturday;
	
	@JsonRawValue
	private String sunday;

	// @JsonProperty("startDate")
	@JsonIgnore
	private String start_date;

	@JsonIgnore
	@JsonProperty("endDate")
	private String end_date;

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getMonday() {
		return monday;
	}

	public void setMonday(String monday) {
		this.monday = monday;
	}

	public String getTuesday() {
		return tuesday;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}

	public String getWednesday() {
		return wednesday;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}

	public String getThursday() {
		return thursday;
	}

	public void setThursday(String thursday) {
		this.thursday = thursday;
	}

	public String getFriday() {
		return friday;
	}

	public void setFriday(String friday) {
		this.friday = friday;
	}

	public String getSaturday() {
		return saturday;
	}

	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}

	public String getSunday() {
		return sunday;
	}

	public void setSunday(String sunday) {
		this.sunday = sunday;
	}

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="PST")
	public Date getStartDate() {
		try {
			SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
			return dt.parse(this.start_date);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="PST")
	public Date getEndDate() {
		try {
			SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
			return dt.parse(this.end_date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

}
