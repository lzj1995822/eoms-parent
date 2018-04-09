package com.eoms.service;

import com.eoms.domain.User;

public interface UserService extends BaseService<User, String> {

    /* 系统登录接口
     * @author asher
     */
    User login(String name,String password);
}
