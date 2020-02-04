package com.leifeng.community.controller;

import com.leifeng.community.mapper.QuestionMapper;
import com.leifeng.community.mapper.UserMapper;
import com.leifeng.community.model.Community_Question;
import com.leifeng.community.model.Community_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by ThinkPad on 2020/1/28.
 */


@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model
    ) {
        //回显浏览器前台页面输入的内容
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        //校验服务端输入的值不能为空
        if ("null".equals(title) || "".equals(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if ("null".equals(description) || "".equals(description)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if ("null".equals(tag) || "".equals(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        Cookie[] cookies = request.getCookies();
        Community_User community_user = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                community_user = userMapper.findByToken(token);
                if (community_user != null) {
                    request.getSession().setAttribute("community_user", community_user);

                }
                break;
            }
        }
        if (community_user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Community_Question community_question = new Community_Question();
        community_question.setTitle(title);
        community_question.setDescription(description);
        community_question.setTag(tag);
        community_question.setGmt_create(System.currentTimeMillis());//创建时间
        community_question.setGmt_modified(community_question.getGmt_modified());//创建者

        questionMapper.create(community_question);

        return "redirect:/";
    }
}
