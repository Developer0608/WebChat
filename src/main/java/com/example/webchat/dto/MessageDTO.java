package com.example.webchat.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class MessageDTO {
    private Long uuid;
    private String from;
    private String to;
    private String message;
    private LocalDateTime time;

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MessageTableDTO [from=" + from + ", message=" + message + ", time=" + time + ", to=" + to + ", uuid="
                + uuid + "]";
    }

}
