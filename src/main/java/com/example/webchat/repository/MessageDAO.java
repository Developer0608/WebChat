package com.example.webchat.repository;

import com.example.webchat.dto.MessageDTO;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveMessage(MessageDTO messageDTO) throws Exception {
        String insertQuery = String.format(
                "INSERT INTO message (uuid, sender, messages, time, receiver) VALUES ('%s', '%s', '%s', '%s', '%s')",
                messageDTO.getUuid(), messageDTO.getSender(), messageDTO.getMessages(), messageDTO.getTime(),
                messageDTO.getReceiver());

        System.out.println("[REPOSITORY]:::[MESSAGEDAO]::::[SAVE]::::insertQuery" + insertQuery);

        jdbcTemplate.execute(insertQuery);
    }

    public List<Map<String, Object>> getMessage(String email) throws Exception {
        String selectQuery = String.format(
                "select sender, receiver, messages from message where receiver = '%s' or sender = '%s'", email, email);

        System.out.println("[REPOSITORY]:::::[MESSAGEDAO]:::[SELECT]::selectQuery" + selectQuery);
        var resultset = jdbcTemplate.queryForList(selectQuery);
        System.out.println("[MESSAGES] :::: " + resultset);

        return resultset;
    }

    public List<Map<String, Object>> getContact(String email) throws Exception {
        String selectQuery = String.format(
                "select distinct u.username, u.email from users u INNER JOIN message m ON (u.email = m.sender or u.email = m.receiver) and (m.sender = '%s' or m.receiver = '%s') and u.email != '%s' ",
                email, email, email);
        System.out.println("[REPOSITORY]:::::[MESSAGEDAO]:::[SELECT]::selectQuery" + selectQuery);
        var resultset = jdbcTemplate.queryForList(selectQuery);
        System.out.println("[MESSAGES] ::: " + resultset);

        return resultset;
    }

}
