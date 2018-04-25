package com.hz.springboot.controller;

import com.hz.springboot.base.BaseController;
import com.hz.springboot.domain.User;
import com.hz.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by HZ-PC on 2018/3/31.
 */
@RequestMapping("/user")
@Controller
public class UserController extends BaseController {
    @Autowired
    private UserService userServiceImpl;

    @RequestMapping(value="/index/{userId}")
    public String index(HttpServletRequest request, @PathVariable Integer userId){
        User user = userServiceImpl.getUserById(userId);
        request.setAttribute("user", user);
        return "user";
    }

    @RequestMapping("/user.html")
    public ModelAndView userList() {
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", userServiceImpl.getUserById(1));
        return modelAndView;
    }

    @RequestMapping(value="/userjson/{userId}")
    @ResponseBody
    public User getUserJson(@PathVariable Integer userId){
        User user = userServiceImpl.getUserById(userId);
        return user;
    }
}
