package com.xudong.community.enums;

/**
 * Created by xuxudong on 2020-03-18 20:01
 */
public enum  NotificationStatusEnum {
    UNREAD(0),READ(1)
    ;
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
