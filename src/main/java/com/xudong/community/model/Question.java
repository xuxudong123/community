package com.xudong.community.model;

import lombok.Data;

/**
 * Created by xuxudong on 2020-03-04 11:21
 */
@Data
public class Question {

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
}
