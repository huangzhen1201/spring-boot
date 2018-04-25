package com.hz.springboot.service.impl;

import com.hz.springboot.dao.SpringBootDao;
import com.hz.springboot.domain.User;
import com.hz.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 这个注解Component 后面的名称不能为userService, 否则会与UserService接口的名称冲突
 * Created by HZ-PC on 2018/3/31.
 */
@Component("userServiceImpl")
public class UserServiceImpl implements UserService{

    @Autowired
    private SpringBootDao springBootDao;

    @Override
    public User getUserById(Integer userId) {
        return springBootDao.getUserById(userId);
    }
}
