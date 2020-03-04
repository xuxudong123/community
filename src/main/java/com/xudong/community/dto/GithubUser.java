package com.xudong.community.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by xuxudong on 2020-02-29 19:36
 */
@Data
public class GithubUser {

    private Long id;
    private String name;
    private String bio;
    private String avatarUrl;
}
