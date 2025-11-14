package com.deliverytech.delivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorResponse {
    private boolean success = false;
    private List<FieldError> errors = new ArrayList<>();
    private Instant timestamp;

    public ValidationErrorResponse() { this.timestamp = Instant.now(); }
    public void addError(String field, String message) { this.errors.add(new FieldError(field, message)); }

    public static class FieldError {
        private String field;
        private String message;
        public FieldError() {}
        public FieldError(String field, String message) { this.field = field; this.message = message; }
        public String getField() { return field; }
        public String getMessage() { return message; }
        public void setField(String field) { this.field = field; }
        public void setMessage(String message) { this.message = message; }
    }

    public boolean isSuccess() { return success; }
    public List<FieldError> getErrors() { return errors; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
