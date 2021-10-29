package com.example.hello.service;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

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

    @Autowired
    private static PasswordUtils passwordUtils;

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

        String salt = "2342034wkljwelrkwjermsdfjkslfs";

        try {
            User userDB = userRepo.getById(user.getEmail());

            boolean isValidated = passwordUtils.verifyUserPassword(user.getPassword(), userDB.getPassword(), salt);

            return isValidated;

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
        // Encrypt Password
        String password = user.getPassword();

        String salt = "2342034wkljwelrkwjermsdfjkslfs";

        String encryptedPassword = passwordUtils.generateSecurePassword(password, salt);

        System.out.println(">>>>>>>>>>PASSWORD" + encryptedPassword);

        user.setPassword(encryptedPassword);

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

    public boolean checkpassword(User user) throws Exception{
        String securedPassword = userDAO.checkpassword(user);
        String password = user.getPassword();
        String salt = "2342034wkljwelrkwjermsdfjkslfs";

        System.out.println("PASSWORD ::::: " + password);

        System.out.println("ENCRYPTED PASSWORD ::::: " + securedPassword);
        
        boolean result = passwordUtils.verifyUserPassword(password, securedPassword, salt);
         
        return result;
    }

    public void updatePassword(User user) throws Exception{
        String salt = "2342034wkljwelrkwjermsdfjkslfs";

        String encryptedPassword = passwordUtils.generateSecurePassword(user.getPassword(), salt);

        System.out.println(">>>>>>>>>>PASSWORD" + encryptedPassword);

        user.setPassword(encryptedPassword);

        userDAO.updatePassword(user);
    }

    public boolean searchByMail(User user) throws Exception{
        boolean result = userDAO.searchByMail(user);

        return result;
    }

    public Map<String, Object> getUserDetail(UserDTO user) throws Exception{
        return  userDAO.getUserDetail(user);
    }
}
