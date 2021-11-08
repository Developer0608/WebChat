package com.example.webchat.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.webchat.dto.MessageDTO;
import com.example.webchat.service.MessageService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin
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
    messageDTO.setMessages(jsonObject.get("message").getAsString());

    UUID uuid = UUID.randomUUID();
    messageDTO.setUuid(uuid);
    LocalDateTime now = LocalDateTime.now();

    messageDTO.setTime(now);

    System.out.println("UUID ::: " + messageDTO.getUuid() + "SENDER :: " + messageDTO.getReceiver() + " "
        + "RECEIVER :: " + messageDTO.getReceiver() + " " + "MESSAGE :: " + messageDTO.getMessages() + " " + "TIME :: "
        + messageDTO.getTime());

    messageService.saveMessage(messageDTO);
    String sender = jsonObject.get("to").getAsString();
    this.simpMessagingTemplate.convertAndSend(String.format("/topic/messages/%s", sender),
        jsonObject.get("message").toString());
  }

  @RequestMapping(value = "/messages/{email}", method = RequestMethod.GET)
  public ResponseEntity<?> message(@PathVariable("email") String email) throws Exception {
    System.out.println("EMAIL :::: " + email);
    System.out.println("I am in messages section");
    List<?> extractedMessage = messageService.getMessage(email);
    System.out.println("MESSAGES ::::: " + extractedMessage);

    return ResponseEntity.ok(extractedMessage);
    // return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(value = "/contact-list/{email}", method = RequestMethod.GET)
  public ResponseEntity<?> contactList(@PathVariable("email") String email) throws Exception {

    List<?> extractedContact = messageService.getContact(email);
    System.out.println("[CONTACT LIST] :::::: " + extractedContact);
    return ResponseEntity.ok(extractedContact);
  }

  @RequestMapping(value = "/search-users/{word}", method = RequestMethod.GET)
  public ResponseEntity<?> searchUsers(@PathVariable("word") String word) throws Exception {

    System.out.println("WORD ::::::  " + word);
    List<?> extractedUsers = messageService.getUsers(word);
    System.out.println("[USERS]::::: " + extractedUsers);
    return ResponseEntity.ok(extractedUsers);
  }

}