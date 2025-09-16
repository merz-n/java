package org.example.productservice.generated.model;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class ErrorResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("details")
    @Valid
    private List<String> details = new ArrayList<>();

    @JsonProperty("resourceGuid")
    private String resourceGuid;

    public ErrorResponse message(String message) {
        this.message = message;
        return this;
    }

    @Schema(required = true, description = "Error message")
    @NotNull
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponse details(List<String> details) {
        this.details = details;
        return this;
    }

    public ErrorResponse addDetailsItem(String detailsItem) {
        this.details.add(detailsItem);
        return this;
    }

    @Schema(required = true, description = "List of error-detailing messages")
    @NotNull
    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public ErrorResponse resourceGuid(String resourceGuid) {
        this.resourceGuid = resourceGuid;
        return this;
    }

    @Schema(description = "GUID of the requested resource")
    public String getResourceGuid() {
        return resourceGuid;
    }

    public void setResourceGuid(String resourceGuid) {
        this.resourceGuid = resourceGuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse errorResponse = (ErrorResponse) o;
        return Objects.equals(message, errorResponse.message) &&
                Objects.equals(details, errorResponse.details) &&
                Objects.equals(resourceGuid, errorResponse.resourceGuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, details, resourceGuid);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", details=" + details +
                ", resourceGuid='" + resourceGuid + '\'' +
                '}';
    }
}
