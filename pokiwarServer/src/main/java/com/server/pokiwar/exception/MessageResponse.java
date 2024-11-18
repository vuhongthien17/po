package com.server.pokiwar.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class MessageResponse<T> {
    private LocalDateTime timestamp;
    private int status;
    private boolean success;
    private String message;
    private T data;

    public MessageResponse(LocalDateTime timestamp, int status, boolean success, T data) {
        this.timestamp = timestamp;
        this.status = status;
        this.success = success;
        this.data = data;
    }

    public MessageResponse(LocalDateTime timestamp, int status, boolean success, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.success = success;
        this.message = message;
    }
}
