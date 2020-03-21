package com.xudong.community.dto;

import lombok.Data;

/**
 * Created by xuxudong on 2020-03-21 17:41
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
