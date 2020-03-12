package com.xudong.community.mapper;

import com.xudong.community.model.Question;
import org.springframework.stereotype.Component;

@Component
public interface QuestionExtMapper {

    int inView(Question question);
    int inCommentCount(Question question);
}