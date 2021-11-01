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

    //function to extract the otp from the DB.
    public int getOtpByEmail(User user) {

        String selectQuery = String.format("SELECT otp FROM users where email = '%s'", user.getEmail());

        System.out.println("[REPOSITORY]::[USERDAO]::[Select]::SelectQuery " + selectQuery);
        var resultSet = jdbcTemplate.queryForList(selectQuery);

        var num = resultSet.get(0).get("otp").toString();
        return Integer.parseInt(num);
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


    //function to extract the user data from the DB.
    public Map<String, Object> getUserDetail(UserDTO user){
        String searchQuery = String.format("select username, email from users where email = '%s'", user.getEmail());

        System.out.println("[REPOSITORY]::[USERDAO]::[Search]::searchQuery " + searchQuery);
        var resultSet = jdbcTemplate.queryForMap(searchQuery);
        System.out.println("RESULTSET :::: " + resultSet);

        return resultSet;
    }

    public Map<String, Object> getUserDetail(String email){
        String searchQuery = String.format("select username, email, password, otp from users where email = '%s'", email);

        System.out.println("[REPOSITORY]::[USERDAO]::[Search]::searchQuery " + searchQuery);
        var resultSet = jdbcTemplate.queryForMap(searchQuery);
        System.out.println("RESULTSET :::: " + resultSet);

        return resultSet;
    }

    public Map<String, Object> updateUser(String email, UserDTO user) {

        String setValues = "";
        if (user.getOtp() != 0) {
            System.out.println("I am setting up OTP");
            setValues += String.format(" otp = %d", user.getOtp());
        }

        if (user.getUsername() != null) {
            setValues += setValues.length() > 0 ? "," : "";
            setValues += String.format("username = '%s'", user.getUsername());
        }

        if (user.getPassword() != null) {
            setValues += setValues.length() > 0 ? "," : "";
            setValues += String.format(" password = '%s'", user.getPassword());
        }

        String updateQuery = String.format("UPDATE users SET %s WHERE email = '%s'", setValues, email);

        System.out.println("[UserDAO]::UpdateUser::updateQuery:: " + updateQuery);
        jdbcTemplate.execute(updateQuery);

        return getUserDetail(new UserDTO(email));
    }
}
