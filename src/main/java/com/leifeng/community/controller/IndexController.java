package com.leifeng.community.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * Created by ThinkPad on 2020/1/2.
 */

@Controller
public class IndexController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
