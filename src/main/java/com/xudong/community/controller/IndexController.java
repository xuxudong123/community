package com.xudong.community.controller;

import com.xudong.community.dto.PaginationDTO;
import com.xudong.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
/**
 * Created by xuxudong on 2020-02-28 15:29
 */

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request, Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "2") Integer size) {

        PaginationDTO paginationDTO = questionService.getQuestionList(page, size);
        model.addAttribute("paginationDTO", paginationDTO);
        return "index";
    }
}
