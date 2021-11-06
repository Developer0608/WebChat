package com.example.webchat.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class MessageDTO {
    private UUID uuid;
    private String sender;
    private String receiver;
    private String messages;
    private LocalDateTime time;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MessageDTO [messages=" + messages + ", receiver=" + receiver + ", sender=" + sender + ", time=" + time
                + ", uuid=" + uuid + "]";
    }
}
