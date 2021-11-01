package com.example.webchat.service;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import com.example.webchat.dto.UserDTO;
import com.example.webchat.model.User;
import com.example.webchat.repository.UserDAO;
import com.example.webchat.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserDAO userDAO;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
	private PasswordEncoder bcryptEncoder;

    @Autowired
    Environment env;

    //function to generate random OTP and call mainsenderservice to send mail.
    public void otpGenerator(UserDTO user) {
        Random rand = new Random();

        int otp_number = rand.nextInt(9999);

        user.setOtp(otp_number);
        String email = user.getEmail();

        userDAO.updateUser(email, user);
        
        // String username = userDAO.getUserName(user);
        // System.out.println("USER = " + user.getEmail() + " " + username);

        System.out.println(otp_number);
        String emailBody = String.format("webchat %s,\n\t\tThis is your OTP to Reset Your Password : %s\n", username,
                otp_number);

        System.out.println("EMAIL BODY ::: " + emailBody);
        mailSenderService.sendmail(user.getEmail(), emailBody, "OTP To Reset Password");
    }

    //functio to extract the otp from the DB.
    public int getOtp(User user) throws Exception {
        int user_otp = userDAO.getOtpByEmail(user);

        return user_otp;
    }

    // function to extract the user details from the DB. 
    public Map<String, Object> getUserDetail(String email) throws Exception{
        return  userDAO.getUserDetail(email);
    }

    public Map<String, Object> getUserDetail(UserDTO user) throws Exception{
        return  userDAO.getUserDetail(user);
    }

    public Map<String, Object> updateUser(String email, UserDTO user) {

        if (user.getPassword() != null) {
            String password = user.getPassword();
            user.setPassword(bcryptEncoder.encode(password));
        }

        return userDAO.updateUser(email, user);
    } 
}
