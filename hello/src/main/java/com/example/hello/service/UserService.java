package com.example.hello.service;

import java.util.Optional;
import java.util.Random;

import com.example.hello.dto.UserDTO;
import com.example.hello.model.User;
import com.example.hello.repository.UserDAO;
import com.example.hello.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserDAO userDAO;

    @Autowired
    private MailSenderService mailSenderService;

    public void otpGenerator(User user) {
        Random rand = new Random();
        int otp_number = rand.nextInt(9999);

        userDAO.update(user, otp_number);

        // send email with OTP
        System.out.println(otp_number);
        String emailBody = String.format("Hello %s,\n This is you OTP to Reset Your Password : %s\n", user.getEmail(),
                otp_number);

        mailSenderService.sendmail(user.getEmail(), emailBody, "OTP To Reset Password");
    }

    public boolean isValidUser(UserDTO user) {

        try {
            User userDB = userRepo.getById(user.getEmail());

            return userDB.getPassword().equals(user.getPassword());

        } catch (Exception ex) {
            System.out.println("[ERROR]::[SERVICE]::isValidUser:: " + ex);
            return false;
        }
    }

    public boolean isAccountExist(UserDTO user) {
        Optional<User> userDB = userRepo.findById(user.getEmail());

        if (userDB.isPresent()) {
            return true;
        }

        return false;
    }

    public boolean isValidEmail(User user) {
        try {
            User userDB = userRepo.getById(user.getEmail());

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void saveUser(User user) throws Exception {
        userDAO.save(user);
    }

    public int getOtp(User user) throws Exception {
        int user_otp = userDAO.getOtpByEmail(user);

        return user_otp;
    }

    public void setPassword(User user) throws Exception {
        userDAO.UpdatePassword(user);
    }

    public void updateUsername(User user) throws Exception{
        userDAO.updateUserName(user);
    }

    public int checkpassword(User user) throws Exception{
        int password = userDAO.checkpassword(user);

        System.out.println("PASSWORD ::::: " + password);

        return password;
        
        
    }

    public void updatePassword(User user) throws Exception{
        userDAO.updatePassword(user);
    }

    public boolean searchByMail(User user) throws Exception{
        boolean result = userDAO.searchByMail(user);

        return result;
    }
}
