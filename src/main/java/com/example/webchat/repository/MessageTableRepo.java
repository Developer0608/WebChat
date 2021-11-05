package com.example.webchat.repository;

import com.example.webchat.model.MessageTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTableRepo extends JpaRepository<MessageTable, String> {

}
