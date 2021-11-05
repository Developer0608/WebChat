package com.example.webchat.dto;

import java.sql.Time;

import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

public class MessageTableDTO {
    private VarcharTypeDescriptor uuid;
    private String from;
    private String to;
    private String message;
    private Time time;

    public VarcharTypeDescriptor getUuid() {
        return uuid;
    }

    public void setUuid(VarcharTypeDescriptor uuid) {
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MessageTableDTO [from=" + from + ", message=" + message + ", time=" + time + ", to=" + to + ", uuid="
                + uuid + "]";
    }
    
}
