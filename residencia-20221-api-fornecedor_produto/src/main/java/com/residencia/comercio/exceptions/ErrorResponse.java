package com.residencia.comercio.exceptions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
	private final int status;
	private final String message;
	private String fields;
    private String fieldsMessage;
	private List<String> details;

	public ErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ErrorResponse(int status, String message, List<String> details) {
		super();
		this.status = status;
		this.message = message;
		this.details = details;
	}

	    public ErrorResponse(int status, String message, String fields, String fieldsMessage,List<String> details ) {
	        super();
	        this.status = status;
	        this.message = message;
	        this.fields = fields;
	        this.fieldsMessage = fieldsMessage;
	    }


	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getFieldsMessage() {
		return fieldsMessage;
	}

	public void setFieldsMessage(String fieldsMessage) {
		this.fieldsMessage = fieldsMessage;
	}
	

}