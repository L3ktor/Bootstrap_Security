package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        return "allUsersPage";
    }

    @GetMapping(value = "/add")
    public String addUser(Model model) {
        List<Role> roles = roleService.allRoles();
        model.addAttribute("roles", roles);
        return "addUser";
    }

    @PostMapping(value = "/add")
    public String postAddUser(@ModelAttribute("user") User user,
                              @RequestParam String login,
                              @RequestParam String password,
                              List<Role> roles){
        user.setLogin(login);
        user.setPassword(password);
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
    public String postEditUser(@PathVariable Long id,
                               @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam List<Role> roles) {
        User user = userService.getUserById(id);
        user.setLogin(username);
        user.setPassword(password);
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
