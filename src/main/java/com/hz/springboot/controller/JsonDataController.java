package com.hz.springboot.controller;

import com.hz.springboot.base.BaseController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HZ-PC on 2018/4/1.
 */
@RestController
@RequestMapping("/json")
public class JsonDataController extends BaseController {

    @RequestMapping("setcache/{val}")
    public String setRedisCache(@PathVariable String val) {
        redisService.set("xx", val);
        return "Set Cache thisCache = " + val;
    }

    @RequestMapping("getcache/{cacheKey}")
    public String getRedisCache(@PathVariable String cacheKey) {
        logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
        return "Get Cache " + cacheKey +  " = " + redisService.get(cacheKey).toString();
    }
}
