package com.example.webchat.controller;
import com.fasterxml.jackson.databind.util.JSONPObject;
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
    
    @MessageMapping("/chat")
    public void greeting(String message) throws Exception {
      System.out.println(">>>>>>>>>>>>> MESSAGE IN SERVER ##############" + message);
    
      JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
      System.out.println("PRINT ++++++++++++++ " + jsonObject.get("to"));
      String sender = jsonObject.get("to").getAsString();
      this.simpMessagingTemplate.convertAndSend(String.format("/topic/messages/%s", sender), jsonObject.get("message").toString());
    }

}