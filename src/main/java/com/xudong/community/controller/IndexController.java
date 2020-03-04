package com.xudong.community.controller;

import com.xudong.community.dto.PaginationDTO;
import com.xudong.community.dto.QuestionDTO;
import com.xudong.community.mapper.QuestionMapper;
import com.xudong.community.mapper.UserMapper;
import com.xudong.community.model.Question;
import com.xudong.community.model.User;
import com.xudong.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xuxudong on 2020-02-28 15:29
 */

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request, Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "2") Integer size) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {


            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    User user = userMapper.getUserByToken(cookie.getValue());
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        PaginationDTO paginationDTO = questionService.getQuestionList(page, size);
        model.addAttribute("paginationDTO", paginationDTO);
        return "index";
    }
}
