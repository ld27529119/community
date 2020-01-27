package com.leifeng.community.controller;

import com.leifeng.community.dto.AccessTokenDto;
import com.leifeng.community.dto.GitHubUser;
import com.leifeng.community.mapper.UserMapper;
import com.leifeng.community.model.Community_User;
import com.leifeng.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by ThinkPad on 2020/1/12.
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client_id}")
    private String client_id;
    @Value("${github.client_secret}")
    private String client_secret;
    @Value("${github.redirect_uri}")
    private String redirect_uri;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(client_id);
        accessTokenDto.setClient_secret(client_secret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirect_uri);
        accessTokenDto.setState(state);
        String accessToken = githubProvider.GetAccessToken(accessTokenDto);
        GitHubUser gitHubUser = githubProvider.getUser(accessToken);
        if (gitHubUser != null) {
            Community_User community_user = new Community_User();
            community_user.setToken(UUID.randomUUID().toString());
            community_user.setName(gitHubUser.getName());
            community_user.setAccount_id(String.valueOf(gitHubUser.getId()));
            community_user.setGmt_create(System.currentTimeMillis());
            community_user.setGmt_modified(community_user.getGmt_modified());
            userMapper.insert(community_user);
            //登录成功 session
            request.getSession().setAttribute("user", gitHubUser);
            return "redirect:/";
        } else {
            //登录不成功
            return "redirect:/";
        }
    }


}
