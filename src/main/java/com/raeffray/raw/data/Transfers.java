package com.raeffray.raw.data;

public class Transfers implements RawData{

	private String from_stop_id;
	private String to_stop_id;
	private String transfer_type;
	private String min_transfer_time;
	public String getFrom_stop_id() {
		return from_stop_id;
	}
	public void setFrom_stop_id(String from_stop_id) {
		this.from_stop_id = from_stop_id;
	}
	public String getTo_stop_id() {
		return to_stop_id;
	}
	public void setTo_stop_id(String to_stop_id) {
		this.to_stop_id = to_stop_id;
	}
	public String getTransfer_type() {
		return transfer_type;
	}
	public void setTransfer_type(String transfer_type) {
		this.transfer_type = transfer_type;
	}
	public String getMin_transfer_time() {
		return min_transfer_time;
	}
	public void setMin_transfer_time(String min_transfer_time) {
		this.min_transfer_time = min_transfer_time;
	}
	
	
}
