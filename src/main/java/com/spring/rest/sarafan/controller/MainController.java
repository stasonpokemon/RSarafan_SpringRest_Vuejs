package com.spring.rest.sarafan.controller;

import com.spring.rest.sarafan.domain.User;
import com.spring.rest.sarafan.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    public MessageRepository messageRepository;

    @GetMapping
    public String main(@AuthenticationPrincipal User user,
                       Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        data.put("profile", user);
        data.put("messages", messageRepository.findAll());
        model.addAttribute("frontendData", data);
        return "index";

    }
}
