package com.xudong.community.dto;

import com.xudong.community.model.User;
import lombok.Data;

/**
 * Created by xuxudong on 2020-03-11 17:03
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private String content;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private User user;
    private Integer commentCount;
}
