package com.eoms.controller;

import com.eoms.domain.User;
import com.eoms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User add(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/list")
    public Page<User> list(@RequestBody User user, @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
                       @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(start, size);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("code", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<User> example = Example.of(user, exampleMatcher);
        return userService.findAll(example, pageable);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id) {
        userService.deleteById(id);
        return true;
    }


}
