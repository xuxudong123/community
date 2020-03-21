package com.xudong.community.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by xuxudong on 2020-03-18 17:03
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
