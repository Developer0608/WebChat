package com.example.webchat.controller;

import com.example.webchat.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {
    @Autowired
    UserRepo repo;

    @RequestMapping("/authenticate")
    public String loginPage() {
        return "authenticate";
    }

    @RequestMapping("/forget")
    public String forget() {
        return "forget";
    }

    @RequestMapping("/otp")
    public String OTP() {
        return "otp";
    }

    @RequestMapping("/setpassword")
    public String SetPassword() {
        return "setpassword";
    }

    @RequestMapping("/")
    public String ChatPage() {
        return "chat";
    }

    @RequestMapping("/setting")
    public String SettingPage(){
        return "setting";
    }

    @RequestMapping("/contact")
    public String ContactPage(){
        return "contact";
    }

    @RequestMapping("/websocket")
    public String socket(){
        return "socket";
    }
}
