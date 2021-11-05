package com.example.webchat.repository;

import com.example.webchat.dto.MessageDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveMessage(MessageDTO messageDTO) throws Exception {
        String insertQuery = String.format(
                "INSERT INTO message (uuid, sender, message, time, receiver) VALUES ('%s', '%s', '%s', '%s', '%s')",
                messageDTO.getUuid(), messageDTO.getSender(), messageDTO.getMessage(), messageDTO.getTime(),
                messageDTO.getReceiver());

        System.out.println("[REPOSITORY]:::[MESSAGEDAO]::::[SAVE]::::insertQuery" + insertQuery);

        jdbcTemplate.execute(insertQuery);
    }

}
