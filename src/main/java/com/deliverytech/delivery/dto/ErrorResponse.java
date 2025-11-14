package com.deliverytech.delivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private boolean success = false;
    private ErrorDetail error;
    private Instant timestamp;

    public ErrorResponse() { this.timestamp = Instant.now(); }
    public ErrorResponse(String code, String message, String details) {
        this.error = new ErrorDetail(code, message, details); this.timestamp = Instant.now();
    }

    public static class ErrorDetail {
        private String code;
        private String message;
        private String details;
        public ErrorDetail() {}
        public ErrorDetail(String code, String message, String details) {
            this.code = code; this.message = message; this.details = details;
        }
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getDetails() { return details; }
        public void setDetails(String details) { this.details = details; }
    }

    public boolean isSuccess() { return success; }
    public ErrorDetail getError() { return error; }
    public void setError(ErrorDetail error) { this.error = error; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
