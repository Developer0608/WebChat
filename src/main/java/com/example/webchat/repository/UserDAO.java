package com.example.webchat.repository;

import java.util.Map;
import com.example.webchat.dto.UserDTO;
import com.example.webchat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //function to Save new user in out DB.
    public void save(User user) throws Exception {
        String insertQuery = String.format("INSERT INTO users (email, password, username) VALUES('%s', '%s', '%s')",
                user.getEmail(), user.getPassword(), user.getUsername());

        System.out.println("[REPOSITORY]::[USERDAO]::[Save]::insertQuery " + insertQuery);
        jdbcTemplate.execute(insertQuery);
    }

    //function to store the sent OTP in our DB.
    public void update(User user, int otp) {

        String updateQuery = String.format("UPDATE users SET otp = %d where email = '%s'", otp, user.getEmail());
        System.out.println("[REPOSITORY]::[USERDAO]::[Update]::updateQuery " + updateQuery);
        jdbcTemplate.execute(updateQuery);
    }

    //function to extract the otp from the DB.
    public int getOtpByEmail(User user) {

        String selectQuery = String.format("SELECT otp FROM users where email = '%s'", user.getEmail());

        System.out.println("[REPOSITORY]::[USERDAO]::[Select]::SelectQuery " + selectQuery);
        var resultSet = jdbcTemplate.queryForList(selectQuery);

        var num = resultSet.get(0).get("otp").toString();
        return Integer.parseInt(num);
    }

    //function to update password in the DB.
    public void UpdatePassword(User user) {
        String updateQuery = String.format("UPDATE users SET password = '%s' where email = '%s'", user.getPassword(),
                user.getEmail());
        System.out.println("[REPOSITORY]::[USERDAO]::[Update]::updateQuery " + updateQuery);
        jdbcTemplate.execute(updateQuery);
    }

    //function to update the username 
    public void updateUserName(User user){
        String updateQuery = String.format("UPDATE users SET username = '%s' where email = '%s'", user.getUsername(), user.getEmail());

        System.out.println("[REPOSITORY]::[USERDAO]::[Update]::updateQuery" + updateQuery);
        jdbcTemplate.execute(updateQuery);
    }

    //function to extract the password from DB.
    public String checkpassword(User user){
        String selectQuery = String.format("SELECT password from users where email = '%s' ", user.getEmail());

        System.out.println("[REPOSITORY]::[USERDAO]::[Select]::SelectQuery " + selectQuery);
        var resultSet = jdbcTemplate.queryForList(selectQuery);
        System.out.println("RESULTSET ::: " + resultSet);

        var num = resultSet.get(0).get("password").toString();
        System.out.println("RETURNING PASSWORD :::: " + num);
        return num;
    }

    //function to update password in the DB.
    public void updatePassword(User user){
        String updateQuery = String.format("UPDATE users SET password = '%s' where email = '%s'", user.getPassword(), user.getEmail());

        System.out.println("[REPOSITORY]::[USERDAO]::[Update]::updateQuery" + updateQuery);
        jdbcTemplate.execute(updateQuery);
    }

    //function to search the user with the help of email.
    public boolean searchByMail(User user){
        String searchQuery = String.format("SELECT email FROM users WHERE email = '%s'", user.getEmail());

        System.out.println("[REPOSITORY]::[USERDAO]::[Search]::searchQuery " + searchQuery);
        var resultSet = jdbcTemplate.queryForList(searchQuery);
        System.out.println("RESULTSET ::: " + resultSet);
        
        if(resultSet.isEmpty()){
            return false;
        }
        
        var num = resultSet.get(0).get("email").toString();
        System.out.println("EMAIL :::: " + num);
        return true;
    }

    //function to extract the user data from the DB.
    public Map<String, Object> getUserDetail(UserDTO user){
        String searchQuery = String.format("select username, email from users where email = '%s'", user.getEmail());

        System.out.println("[REPOSITORY]::[USERDAO]::[Search]::searchQuery " + searchQuery);
        var resultSet = jdbcTemplate.queryForMap(searchQuery);
        System.out.println("RESULTSET :::: " + resultSet);

        return resultSet;
    }

    //function to extract the username from DB.
    public String getUserName(User user){
        String searchQuery = String.format("select username from users where email = '%s'", user.getEmail());

        System.out.println("[REPOSITORY]::[USERDAO]::[Search]::searchQuery " + searchQuery);
        var resultSet = jdbcTemplate.queryForList(searchQuery);
        System.out.println("RESULTSET ::: " + resultSet);

        var num = resultSet.get(0).get("username").toString();
        System.out.println("EMAIL :::: " + num);

        return num;
    }
}
