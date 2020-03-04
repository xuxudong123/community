package com.xudong.community.service;

import com.xudong.community.dto.PaginationDTO;
import com.xudong.community.dto.QuestionDTO;
import com.xudong.community.mapper.QuestionMapper;
import com.xudong.community.mapper.UserMapper;
import com.xudong.community.model.Question;
import com.xudong.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuxudong on 2020-03-04 15:24
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO getQuestionList(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }

        //size*(page-1)
        Integer offset = size * (page - 1);

        List<Question> questionList = questionMapper.getQuestionList(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

//        PaginationDTO paginationDTO = new PaginationDTO();

        for (Question question : questionList) {
            User user = userMapper.getUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOList);
//        Integer totalCount = questionMapper.count();
//        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }
}
