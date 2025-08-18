package org.example.controller;

import org.example.dao.JdbcUserDao;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JdbcUserDao jdbcUserDao;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", jdbcUserDao.findAll());
        return "user-list";
    }

    @GetMapping("/{id}")
    public String getUser(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("user", jdbcUserDao.findById(id));
        return "user-details";
    }

    @GetMapping("/add")
    public String getAddUserPage(Model model) {
        return "user-add";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user){
        jdbcUserDao.create(user);
        return "redirect:/users";
    }

    @GetMapping("/basic/{id}")
    public String getBasicUserById(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("user", userService.getBasicUserById(id));
        return "basic-user-details";
    }
}
