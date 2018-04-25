package com.hz.springboot.service;

import com.hz.springboot.domain.User;

/**
 * Created by HZ-PC on 2018/3/31.
 */
public interface UserService {
    User getUserById(Integer userId);
}
