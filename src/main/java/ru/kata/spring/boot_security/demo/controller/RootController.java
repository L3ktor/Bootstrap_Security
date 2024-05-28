package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RootController {
    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("Welcome to Spring Boot!");
        model.addAttribute("messages", messages);
        return "helloPage";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "loginPage";
    }

    @GetMapping("/vip")
    public String getVipPage(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("Welcome to Spring Boot!");
        model.addAttribute("messages", messages);
        return "vipPage";
    }
}
