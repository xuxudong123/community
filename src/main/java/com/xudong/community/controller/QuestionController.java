package com.xudong.community.controller;

import com.xudong.community.dto.QuestionDTO;
import com.xudong.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by xuxudong on 2020-03-09 14:38
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model){

        QuestionDTO questionDTO = questionService.getById(id);
        //累加阅读数
        questionService.inView(id);
        model.addAttribute("question",questionDTO);

        return "question";
    }
}
