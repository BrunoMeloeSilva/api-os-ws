package com.github.brunomeloesilva.os.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ProblemMessage {
	
	private OffsetDateTime timestamp;
	private Integer httpStatus;
	private String titleMessage;
	private List<FieldDetails> fieldDetails;
	
	public static final class FieldDetails {
		private String name;
		private String message;
		
		public FieldDetails(String errorCampo, String defaultMessage) {
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		} 
	}

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(OffsetDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getTitleMessage() {
		return titleMessage;
	}

	public void setTitleMessage(String titleMessage) {
		this.titleMessage = titleMessage;
	}

	public List<FieldDetails> getFieldDetails() {
		return fieldDetails;
	}

	public void setFieldDetails(List<FieldDetails> fieldDetails) {
		this.fieldDetails = fieldDetails;
	}

	public ProblemMessage(OffsetDateTime timestamp, Integer httpStatus, String titleMessage,
			List<FieldDetails> fieldDetails) {
		super();
		this.timestamp = timestamp;
		this.httpStatus = httpStatus;
		this.titleMessage = titleMessage;
		this.fieldDetails = fieldDetails;
	}
	
	
}
