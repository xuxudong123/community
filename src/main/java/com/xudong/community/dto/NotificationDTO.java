package com.xudong.community.dto;

import com.xudong.community.model.User;
import lombok.Data;

/**
 * Created by xuxudong on 2020-03-18 20:21
 */
@Data
public class NotificationDTO {
    private Long id;

    private Long gmtCreate;

    private Integer status;

    private Long notifier;

    private String notifierName;

    private String outerTitle;

    private Long outerid;

    private String typeName;

    private Integer type;

}
