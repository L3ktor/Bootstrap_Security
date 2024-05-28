package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/kab")
    public String getUserPage(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "userpage";
    }
@GetMapping("/{id}")
public String show (@PathVariable("id") long id, Model model) {
    model.addAttribute("user", userService.getUserById(id));
    return "userpage";
}

}
