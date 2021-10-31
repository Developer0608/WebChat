package com.example.hello.controller;

import java.util.Map;

import com.example.hello.dto.UserDTO;
import com.example.hello.model.User;
import com.example.hello.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    Environment env;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public void test() {
        System.out.println(env.getProperty("spring.chat.username"));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody UserDTO user) throws Exception {
        if (userService.isValidUser(user)) {
            // Fetch user details
            
            Map<String, Object> result = userService.getUserDetail(user);
            return new ResponseEntity<>(result, null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity registerUser(@RequestBody UserDTO userParam) throws Exception {
        User user = new User();

        user.setUsername(userParam.getUsername());
        user.setEmail(userParam.getEmail());
        user.setPassword(userParam.getPassword());

        if (!userService.isAccountExist(userParam)) {
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/sendotp", method = RequestMethod.POST)
    public ResponseEntity sendotp(@RequestBody UserDTO userParam) throws InterruptedException {
        System.out.println(">>>>>>>>>" + userParam.getEmail());
        User user = new User();

        user.setEmail(userParam.getEmail());

        if (userService.isValidEmail(user)) {
            userService.otpGenerator(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/checkotp", method = RequestMethod.POST)
    public ResponseEntity checkotp(@RequestBody UserDTO userParam) throws Exception {
        System.out.println(">>>>>>>>>>>>" + userParam.getOtp() + ">>>>>" + userParam.getEmail());

        User user = new User();

        user.setEmail(userParam.getEmail());
        int user_otp = userService.getOtp(user);

        if (user_otp == userParam.getOtp()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/setpassword", method = RequestMethod.POST)
    public ResponseEntity setpassword(@RequestBody UserDTO userParam) throws Exception {

        System.out.println("Set>>>>>>>>" + userParam.getPassword() + ">>>>>>>>>>" + userParam.getEmail());

        User user = new User();

        user.setPassword(userParam.getPassword());
        user.setEmail(userParam.getEmail());

        userService.updatePassword(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseEntity updateprofile(@RequestBody UserDTO userParam) throws Exception {
        System.out.println("Set >>>>>>>>>>>>>> " + userParam.getUsername() + ">>>>> " + userParam.getEmail());
        
        User user = new User();

        user.setUsername(userParam.getUsername());
        user.setEmail(userParam.getEmail());

        userService.updateUsername(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/checkpassword", method = RequestMethod.POST)
    public ResponseEntity passwordCheck(@RequestBody UserDTO userParam) throws Exception {
        System.out.println("Password :::::: " + userParam.getPassword() + "Email :: " + userParam.getEmail());
        
        User user = new User();

        user.setEmail(userParam.getEmail());
        user.setPassword(userParam.getPassword());

        boolean result = userService.checkpassword(user);
        
         
        if(result == true){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);      
    }


    @RequestMapping(value = "/searchByMail", method = RequestMethod.POST)
    public ResponseEntity searchByMail(@RequestBody UserDTO userParam) throws Exception{
        System.out.println("EMAIL :::::::" + userParam.getEmail());

        User user = new User();

        user.setEmail(userParam.getEmail());
        boolean result = userService.searchByMail(user);
        
        if(result == true){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
