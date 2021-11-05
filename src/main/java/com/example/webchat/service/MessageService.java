package com.example.webchat.service;

import com.example.webchat.dto.MessageDTO;
import com.example.webchat.repository.MessageDAO;
import com.example.webchat.repository.MessageRepo;

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
        messageDAO.saveMessage(messageDTO);
    }
}
