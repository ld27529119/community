package com.leifeng.community.controller;

import com.leifeng.community.mapper.UserMapper;
import com.leifeng.community.model.Community_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by ThinkPad on 2020/1/2.
 */

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                Community_User community_user = userMapper.findByToken(token);
                if (community_user != null) {
                    request.getSession().setAttribute("community_user", community_user);

                }
                break;
            }
        }
        return "index";
    }
}
