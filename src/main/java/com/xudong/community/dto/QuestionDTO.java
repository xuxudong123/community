package com.xudong.community.dto;

import com.xudong.community.model.User;
import lombok.Data;

/**
 * Created by xuxudong on 2020-03-04 15:22
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;

    private User user;
}
