package com.xudong.community.dto;

import org.springframework.stereotype.Component;

/**
 * Created by xuxudong on 2020-02-29 19:36
 */
public class GithubUser {

    private Long id;
    private String name;
    private String bio;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }
}
