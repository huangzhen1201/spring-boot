package com.hz.springboot.dao;

import com.hz.springboot.domain.User;

/**
 * Created by HZ-PC on 2018/3/31.
 */
public interface SpringBootDao {
    User getUserById(Integer userId);
}
