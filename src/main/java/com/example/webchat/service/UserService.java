package com.example.webchat.service;

import java.util.Map;
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

    private String messageTemplate(int otp) {
        String messageHTMLString = "<!doctype html> \n"
        +"<html lang='en-US'> \n"
        +"<head> \n"
        +"    <meta content='text/html; charset=utf-8' http-equiv='Content-Type' /> \n"
        +"    <title>Reset Password Email Template</title> \n"
        +"    <meta name='description' content='Reset Password Email Template.'> \n"
        +"    <style type='text/css'> \n"
        +"        a:hover {text-decoration: underline !important;} \n"
        +"    </style> \n"
        +"</head> \n"
        +"<body marginheight='0' topmargin='0' marginwidth='0' style='margin: 0px; background-color: #f2f3f8;' leftmargin='0'> \n"
        +"    <!--100% body table--> \n"
        +"    <table cellspacing='0' border='0' cellpadding='0' width='100%' bgcolor='#f2f3f8' \n"
        +"        style='@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;'> \n"
        +"        <tr> \n"
        +"            <td> \n"
        +"                <table style='background-color: #f2f3f8; max-width:670px;  margin:0 auto;' width='100%' border='0' \n"
        +"                    align='center' cellpadding='0' cellspacing='0'> \n"
        +"                    <tr> \n"
        +"                        <td style='height:80px;'>&nbsp;</td> \n"
        +"                    </tr> \n"
        +"                    <tr> \n"
        +"                         \n"
        +"                    </tr> \n"
        +"                    <tr> \n"
        +"                        <td style='height:20px;'>&nbsp;</td> \n"
        +"                    </tr> \n"
        +"                    <tr> \n"
        +"                        <td> \n"
        +"                            <table width='95%' border='0' align='center' cellpadding='0' cellspacing='0' \n"
        +"                                style='max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);'> \n"
        +"                                <tr> \n"
        +"                                    <td style='height:40px;'>&nbsp;</td> \n"
        +"                                </tr> \n"
        +"                                <tr> \n"
        +"                                    <td style='padding:0 35px;'> \n"
        +"                                        <h1 style='color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;'>You have \n"
        +"                                            requested to reset your password</h1> \n"
        +"                                        <span \n"
        +"                                            style='display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;'></span> \n"
        +"                                        <p style='color:#455056; font-size:15px;line-height:24px; margin:0;'> \n"
        +"                                            We cannot simply send you your old password. A OTP is generated for you. \n"
        +"                                        </p> \n"
        +"                                        <a href='javascript:void(0);' \n"
        +"                                            style='background:#20e277;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;'>"+otp+"</a> \n"
        +"                                    </td> \n"
        +"                                </tr> \n"
        +"                                <tr> \n"
        +"                                    <td style='height:40px;'>&nbsp;</td> \n"
        +"                                </tr> \n"
        +"                            </table> \n"
        +"                        </td> \n"
        +"                    <tr> \n"
        +"                        <td style='height:20px;'>&nbsp;</td> \n"
        +"                    </tr> \n"
        +"                    <tr> \n"
        +"                         \n"
        +"                    </tr> \n"
        +"                    <tr> \n"
        +"                        <td style='height:80px;'>&nbsp;</td> \n"
        +"                    </tr> \n"
        +"                </table> \n"
        +"            </td> \n"
        +"        </tr> \n"
        +"    </table> \n"
        +"    <!--/100% body table--> \n"
        +"</body> \n"
        +"</html> \n";

        return messageHTMLString;
    }

    //function to generate random OTP and call mail sender service to send mail.
    public void otpGenerator(UserDTO user) {
        int otp_number = (int)Math.floor(Math.random()*(9999-1000+1)+1000);

        user.setOtp(otp_number);

        String email = user.getEmail();
        userDAO.updateUser(email, user);
        
        String emailBody = messageTemplate(user.getOtp());
        try {
            mailSenderService.sendmail(user.getEmail(), emailBody, "OTP To Reset Password", 0);            
        } catch (Exception e) {
            System.err.println("[Error]::OTPGenerator::"+ e);
        }
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

    public boolean validatePassword(UserDTO user){
        String email = user.getEmail();

        String password = userDAO.checkpassword(email);

        return bcryptEncoder.matches(user.getPassword(),password);
         
    }
}
