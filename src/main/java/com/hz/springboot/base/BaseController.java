package com.hz.springboot.base;

import com.hz.springboot.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by HZ-PC on 2018/4/1.
 */
@Controller
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public RedisService redisService;
}
