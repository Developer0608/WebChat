package com.example.webchat.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.webchat.dto.MessageDTO;
import com.example.webchat.service.MessageService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @Autowired
  MessageDTO messageDTO;

  @Autowired
  MessageService messageService;

  @MessageMapping("/chat")
  public void greeting(String message) throws Exception {
    System.out.println(">>>>>>>>>>>>> MESSAGE IN SERVER ##############" + message);

    JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
    System.out.println("PRINT ++++++++++++++ " + jsonObject.get("to"));

    messageDTO.setSender(jsonObject.get("from").getAsString());
    messageDTO.setReceiver(jsonObject.get("to").getAsString());
    messageDTO.setMessage(jsonObject.get("message").getAsString());

    UUID uuid = UUID.randomUUID();
    messageDTO.setUuid(uuid);
    LocalDateTime now = LocalDateTime.now();

    messageDTO.setTime(now);

    System.out.println("UUID ::: " + messageDTO.getUuid() + "SENDER :: " + messageDTO.getReceiver() + " "
        + "RECEIVER :: " + messageDTO.getReceiver() + " " + "MESSAGE :: " + messageDTO.getMessage() + " " + "TIME :: "
        + messageDTO.getTime());

    messageService.saveMessage(messageDTO);
    String sender = jsonObject.get("to").getAsString();
    this.simpMessagingTemplate.convertAndSend(String.format("/topic/messages/%s", sender),
        jsonObject.get("message").toString());
  }

}