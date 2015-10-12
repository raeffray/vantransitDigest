package com.raeffray.rest.cient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
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

	private void addOperation(Operation operation) {
		if (operations == null) {
			operations = new ArrayList<BatchOperationRequest.Operation>();
		}
		operations.add(operation);
	}

	@JsonInclude(Include.NON_NULL)
	class Operation {

		private int id;

		private String method;

		private String to;

		@JsonRawValue
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

	public void addOperation(int id, String method, String reosurce, String body) {
		this.addOperation(new Operation(id, method, reosurce, body));	
	}
	
	public String parseJson() {
		try {
			ObjectWriter ow = new ObjectMapper().writer();
			return ow.writeValueAsString(this.getOperations());
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
