package com.example.hello.repository;

import java.util.Random;

import com.example.hello.model.User;
import com.example.hello.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.var;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User user) throws Exception {
        String insertQuery = String.format("INSERT INTO users (email, password, username) VALUES('%s', '%s', '%s')",
                user.getEmail(), user.getPassword(), user.getUsername());

        System.out.println("[REPOSITORY]::[USERDAO]::[Save]::insertQuery " + insertQuery);
        /**
         * DML - execute INSERT, UPDATE, DELETE
         */
        jdbcTemplate.execute(insertQuery);
    }

    public void update(User user, int otp) {

        String updateQuery = String.format("UPDATE users SET otp = %d where email = '%s'", otp, user.getEmail());
        System.out.println("[REPOSITORY]::[USERDAO]::[Update]::updateQuery " + updateQuery);
        jdbcTemplate.execute(updateQuery);
    }

    public int getOtpByEmail(User user) {

        String selectQuery = String.format("SELECT otp FROM users where email = '%s'", user.getEmail());

        System.out.println("[REPOSITORY]::[USERDAO]::[Select]::SelectQuery " + selectQuery);
        var resultSet = jdbcTemplate.queryForList(selectQuery);

        var num = resultSet.get(0).get("otp").toString();
        return Integer.parseInt(num);
    }

    public void UpdatePassword(User user) {
        String updateQuery = String.format("UPDATE users SET password = '%s' where email = '%s'", user.getPassword(),
                user.getEmail());
        System.out.println("[REPOSITORY]::[USERDAO]::[Update]::updateQuery " + updateQuery);
        jdbcTemplate.execute(updateQuery);
    }

    public void updateUserName(User user){
        String updateQuery = String.format("UPDATE users SET username = '%s' where email = '%s'", user.getUsername(), user.getEmail());

        System.out.println("[REPOSITORY]::[USERDAO]::[Update]::updateQuery" + updateQuery);
        jdbcTemplate.execute(updateQuery);
    }
}
