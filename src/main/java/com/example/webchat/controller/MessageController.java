package com.example.webchat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @MessageMapping("/chat")
    @SendTo("/topic/app")
    public String greeting(String message) throws Exception {
        System.out.println(">>>>>>>>>>>>> MESSAGE IN SERVER ##############" + message);
      Thread.sleep(1000); // simulated delay
      return "FROM SERVER";
    //   return new Greeting("webchat, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}