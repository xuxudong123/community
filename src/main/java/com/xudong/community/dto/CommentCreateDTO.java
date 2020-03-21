package com.xudong.community.dto;

import lombok.Data;

/**
 * Created by xuxudong on 2020-03-11 17:03
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
