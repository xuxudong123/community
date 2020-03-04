package com.xudong.community.model;

import lombok.Data;
/**
 * Created by xuxudong on 2020-03-02 17:22
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long mgtCreate;
    private Long mgtModified;
    private String avatarUrl;
}
