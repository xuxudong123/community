package com.xudong.community.dto;

import lombok.Data;

/**
 * Created by xuxudong on 2020-02-29 18:35
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
