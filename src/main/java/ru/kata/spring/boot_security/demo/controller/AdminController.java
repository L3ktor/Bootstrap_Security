package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;



@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping(value = "/all")
    public String allUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "adminPage";
    }

    @GetMapping(value = "/add")
    public String addUser(Model model) {
        List<Role> roles = roleService.allRoles();
        model.addAttribute("roles", roles);
        return "addUser";
    }

    @PostMapping(value = "/add")
    public String postAddUser(@ModelAttribute("user") User user,
                              @RequestParam List<Role> roles){
        user.getRoles().addAll(roles);
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        List<Role> roles = roleService.allRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        userService.editUser(user);
        return "editUser";
    }
    @PostMapping(value = "/edit")
    public String postEditUser(@ModelAttribute("user") User user,
                               @RequestParam List<Role> roles) {
        user.getRoles().addAll(roles);
        userService.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

}
