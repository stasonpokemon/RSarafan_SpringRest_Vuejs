package com.spring.rest.sarafan.controller;

import com.spring.rest.sarafan.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private List<Map<String,String>> messages = new ArrayList<Map<String,String>>(){{
        add(new HashMap<String,String>(){{ put("id","1"); put("text","First message");}});
        add(new HashMap<String,String>(){{ put("id","2"); put("text","Second message");}});
        add(new HashMap<String,String>(){{ put("id","3"); put("text","Third message");}});
    }};

    @GetMapping
    public List<Map<String,String>> list() {
        return messages;
    }

    @GetMapping("/{id}")
    public Map<String,String> findMessage(@PathVariable("id") String messageById){
        return getMessage(messageById);
    }

    private Map<String, String> getMessage(String messageById) {
        return messages.stream().filter(message -> message.get("id").equals(messageById))
                .findFirst().orElseThrow(() -> new NotFoundException());
    }

    @PostMapping
    public Map<String,String> addMessage(@RequestBody Map<String,String> message) {
        int size = messages.size();
        message.put("id", String.valueOf(++size));
        messages.add(message);
        return message;
    }

    @PutMapping("/{id}")
    public Map<String,String> updateMessage(@PathVariable("id") String id,
                                            @RequestBody Map<String,String> message){
        Map<String, String> messageFromDB = getMessage(id);
        messageFromDB.putAll(message);
        messageFromDB.put("id",id);

        return messageFromDB;
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable("id") String id){
        Map<String, String> message = getMessage(id);
        messages.remove(message);
    }
}
