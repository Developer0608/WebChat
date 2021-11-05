package com.example.webchat.controller;

import java.time.LocalDateTime;

import com.example.webchat.dto.MessageDTO;

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

  @MessageMapping("/chat")
  public void greeting(String message) throws Exception {
    System.out.println(">>>>>>>>>>>>> MESSAGE IN SERVER ##############" + message);

    JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
    System.out.println("PRINT ++++++++++++++ " + jsonObject.get("to"));

    messageDTO.setFrom(jsonObject.get("from").toString());
    messageDTO.setTo(jsonObject.get("to").toString());
    messageDTO.setMessage(jsonObject.get("message").toString());

    LocalDateTime now = LocalDateTime.now();

    messageDTO.setTime(now);

    System.out.println("FROM :: " + messageDTO.getFrom() + " " + "TO :: " + messageDTO.getTo() + " " + "MESSAGE :: "
        + messageDTO.getMessage() + " " + "TIME :: " + messageDTO.getTime());

    String sender = jsonObject.get("to").getAsString();
    this.simpMessagingTemplate.convertAndSend(String.format("/topic/messages/%s", sender),
        jsonObject.get("message").toString());
  }

}