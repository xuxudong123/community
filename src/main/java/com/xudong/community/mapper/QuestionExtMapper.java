package com.xudong.community.mapper;

import com.xudong.community.dto.QuestionQueryDTO;
import com.xudong.community.model.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionExtMapper {

    int inView(Question question);
    int inCommentCount(Question question);
    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}