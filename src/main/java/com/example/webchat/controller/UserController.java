package com.example.webchat.controller;

import com.example.webchat.dto.UserDTO;
import com.example.webchat.model.User;
import com.example.webchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    Environment env;


    @CrossOrigin
    @RequestMapping(value = "/send-otp", method = RequestMethod.POST)
    public ResponseEntity<?> sendotp(@RequestBody UserDTO userParam) throws Exception {
        UserDTO user = new UserDTO(userParam.getEmail());

        user.setEmail(userParam.getEmail());
        String email = user.getEmail();

        var result = userService.getUserDetail(email);

        if (!result.isEmpty()) {
            user.setUsername(result.get("username").toString());
            userService.otpGenerator(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/check-otp", method = RequestMethod.POST)
    public ResponseEntity<?> checkotp(@RequestBody UserDTO userParam) throws Exception {
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
    public ResponseEntity<?> setpassword(@RequestBody UserDTO userParam) throws Exception {

        System.out.println("Set>>>>>>>>" + userParam.getPassword() + ">>>>>>>>>>" + userParam.getEmail());
        String email = userParam.getEmail();

        userService.updateUser(email , userParam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/checkpassword", method = RequestMethod.POST)
    public ResponseEntity<?> passwordCheck(@RequestBody UserDTO userParam) throws Exception {
        System.out.println("Password :::::: " + userParam.getPassword() + "Email :: " + userParam.getEmail());

        if(userService.validatePassword(userParam) == true){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/users/{email}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("email") String email, @RequestBody UserDTO userParam) {
        return ResponseEntity.ok(userService.updateUser(email, userParam));
    }

    @RequestMapping(value = "/users/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("email") String email) throws Exception {
        UserDTO user = new UserDTO(email);

        return ResponseEntity.ok(userService.getUserDetail(user));
    }

}
