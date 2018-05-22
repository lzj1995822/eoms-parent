package com.eoms.service.impl;

import com.eoms.domain.User;
import com.eoms.repository.BaseRepository;
import com.eoms.repository.UserRepository;
import com.eoms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiseImpl<User, String> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected BaseRepository<User, String> getBaseRepository() {
        return userRepository;
    }

    @Override
    public User login(String name, String password) {
        return userRepository.findByNameAndPassword(name,password);
    }
}
