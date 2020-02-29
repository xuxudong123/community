package com.xudong.community.controller;

import com.sun.org.glassfish.gmbal.ParameterNames;
import com.xudong.community.dto.AccessTokenDTO;
import com.xudong.community.dto.GithubUser;
import com.xudong.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xuxudong on 2020-02-29 18:23
 */
@Controller
public class AutnorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state") String state){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("Iv1.4074cf876049ffa6");
        accessTokenDTO.setClient_secret("26dd61365c49b489b7a13b294a149a6680487c13");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());

        return "index";
    }
}
