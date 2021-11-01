package com.example.webchat.service;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import com.example.webchat.dto.UserDTO;
import com.example.webchat.model.User;
import com.example.webchat.repository.UserDAO;
import com.example.webchat.repository.UserRepo;
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

    //function to generate random OTP and call mainsenderservice to send mail.
    public void otpGenerator(User user) {
        Random rand = new Random();

        int otp_number = rand.nextInt(9999);
        userDAO.update(user, otp_number);
        
        String username = userDAO.getUserName(user);
        System.out.println("USER = " + user.getEmail() + " " + username);

        System.out.println(otp_number);
        String emailBody = String.format("webchat %s,\n\t\tThis is your OTP to Reset Your Password : %s\n", username,
                otp_number);

        System.out.println("EMAIL BODY ::: " + emailBody);
        mailSenderService.sendmail(user.getEmail(), emailBody, "OTP To Reset Password");
    }

    //function to validate user credentials 
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

    //function to check  user account exist or not
    public boolean isAccountExist(UserDTO user) {
        Optional<User> userDB = userRepo.findById(user.getEmail());

        if (userDB.isPresent()) {
            return true;
        }

        return false;
    }

    //functio to check provided email is valid or not.
    public boolean isValidEmail(User user) {
        try {
            User userDB = userRepo.getById(user.getEmail());

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //functio to save user in the DB.
    public void saveUser(User user) throws Exception {
        // Encrypt Password
        String password = user.getPassword();

        String salt = "2342034wkljwelrkwjermsdfjkslfs";
        String encryptedPassword = passwordUtils.generateSecurePassword(password, salt);

        System.out.println(">>>>>>>>>>PASSWORD" + encryptedPassword);
        user.setPassword(encryptedPassword);

        userDAO.save(user);
    }

    //functio to extract the otp from the DB.
    public int getOtp(User user) throws Exception {
        int user_otp = userDAO.getOtpByEmail(user);

        return user_otp;
    }

    //function to update password in the DB.
    public void setPassword(User user) throws Exception {
        userDAO.UpdatePassword(user);
    }

    //function to update username in the DB.
    public void updateUsername(User user) throws Exception{
        userDAO.updateUserName(user);
    }

    //functio to check provided password and DB password is same or not.
    public boolean checkpassword(User user) throws Exception{
        String securedPassword = userDAO.checkpassword(user);
        String password = user.getPassword();
        String salt = "2342034wkljwelrkwjermsdfjkslfs";

        System.out.println("PASSWORD ::::: " + password);

        System.out.println("ENCRYPTED PASSWORD ::::: " + securedPassword);
        boolean result = passwordUtils.verifyUserPassword(password, securedPassword, salt);
         
        return result;
    }

    //function to update password in the DB.
    public void updatePassword(User user) throws Exception{
        String salt = "2342034wkljwelrkwjermsdfjkslfs";

        String encryptedPassword = passwordUtils.generateSecurePassword(user.getPassword(), salt);
        System.out.println(">>>>>>>>>>PASSWORD" + encryptedPassword);

        user.setPassword(encryptedPassword);
        userDAO.updatePassword(user);
    }

    //function to perform search operation in the DB with the help of email.
    public boolean searchByMail(User user) throws Exception{
        boolean result = userDAO.searchByMail(user);

        return result;
    }

    //function to extract the user details from the DB. 
    public Map<String, Object> getUserDetail(UserDTO user) throws Exception{
        return  userDAO.getUserDetail(user);
    }
}
