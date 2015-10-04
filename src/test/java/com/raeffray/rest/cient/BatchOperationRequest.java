package com.raeffray.rest.cient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class BatchOperationRequest implements Serializable {
	
	private static final long serialVersionUID = 1676494230202649146L;

	private Collection<Operation> operations;

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}

	private void addOperations(Operation operation) {
		if (operations == null) {
			operations = new ArrayList<BatchOperationRequest.Operation>();
		}
		operations.add(operation);
	}

	class Operation {

		private int id;

		private String method;

		private String to;

		private String body;

		public int getId() {
			return id;
		}

		public String getMethod() {
			return method;
		}

		public String getTo() {
			return to;
		}

		public String getBody() {
			return body;
		}

		public Operation(int id, String method, String to, String body) {
			super();
			this.id = id;
			this.method = method;
			this.to = to;
			this.body = body;
		}

	}

	public void createOperation(int id, String method, String reosurce, String body) {
		this.addOperations(new Operation(id, method, reosurce, body));	
	}
	
	public String parseJson() throws Exception{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(this.getOperations());
	}

}
