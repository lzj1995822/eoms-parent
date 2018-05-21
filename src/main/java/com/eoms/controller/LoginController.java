package com.eoms.controller;

import com.eoms.domain.User;
import com.eoms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public User login(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
        User user = userService.login(name,password);
        return  user;
    }
}
