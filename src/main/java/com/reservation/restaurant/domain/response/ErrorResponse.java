package com.reservation.restaurant.domain.response;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public class ErrorResponse {

    private HttpStatus status;
    private String message;
    private Instant timestamp;

    public ErrorResponse(HttpStatus status, String message, Instant timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }
}