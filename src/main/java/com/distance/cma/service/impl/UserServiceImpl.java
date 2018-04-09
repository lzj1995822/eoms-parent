package com.distance.cma.service.impl;

import com.distance.cma.domain.User;
import com.distance.cma.repository.BaseRepository;
import com.distance.cma.repository.UserRepository;
import com.distance.cma.service.UserService;
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
}
