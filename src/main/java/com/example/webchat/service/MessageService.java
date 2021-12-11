package com.example.webchat.service;

import java.util.List;

import com.example.webchat.dto.MessageDTO;
import com.example.webchat.repository.MessageDAO;
import com.example.webchat.repository.MessageRepo;

// import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    MessageDTO messageDTO;

    public void saveMessage(MessageDTO messageDTO) throws Exception {
        // messageDTO.setMessages(bcryptEncoder.encode(messageDTO.getMessages()));
        messageDAO.saveMessage(messageDTO);
    }

    public List<?> getMessage(String email) throws Exception {
        return messageDAO.getMessage(email);
    }

    public List<?> getContact(String email) throws Exception {
        return messageDAO.getContact(email);
    }

    public List<?> getUsers(String word) throws Exception {
        return messageDAO.getUsers(word);
    }
}
