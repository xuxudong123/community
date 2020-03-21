package com.xudong.community.controller;

import com.xudong.community.dto.NotificationDTO;
import com.xudong.community.dto.PaginationDTO;
import com.xudong.community.enums.NotificationTypeEnum;
import com.xudong.community.model.User;
import com.xudong.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xuxudong on 2020-03-18 23:20
 */
@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,@PathVariable(name = "id") Long id){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id,user);

        if(NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType() || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()){
            return  "redirect:/question/" + notificationDTO.getOuterid();
        }else {
            return "redirect:/";
        }
    }
}
