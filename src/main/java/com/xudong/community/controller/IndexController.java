package com.xudong.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xuxudong on 2020-02-28 15:29
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String hello() {
        return "index";
    }
}
