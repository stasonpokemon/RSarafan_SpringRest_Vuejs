package com.spring.rest.sarafan.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.spring.rest.sarafan.domain.Message;
import com.spring.rest.sarafan.domain.Views;
import com.spring.rest.sarafan.repo.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {


    @Autowired
    public MessageRepository messageRepository;



    @GetMapping
    @JsonView(Views.IdText.class)
    public List<Message> list() {
        return messageRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Views.FullMessage.class)
    public Message findMessage(@PathVariable("id") Message message){
        return message;
    }

    @PostMapping
    public Message addMessage(@RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable("id") Message messageFromDb,
                                 @RequestBody Message message){
        // copy field from message to messageFromDb, except id field
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepository.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable("id") Message message){
        messageRepository.delete(message);
    }
}
