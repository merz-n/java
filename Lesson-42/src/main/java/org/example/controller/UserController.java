package org.example.controller;

import org.example.dao.JdbcUserDao;
import org.example.dao.UserDao;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userDao.findAll());
        return "user-list";
    }

    @GetMapping("/add")
    public String getAddUserPage(Model model) {
        model.addAttribute("user", new User());
        return "user-add";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user){
        userDao.create(user);
        return "redirect:/users";
    }
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") int id, Model model){
        User user = userDao.findById(id);
        model.addAttribute("user", user);
        return "user-by-id";
    }
}
