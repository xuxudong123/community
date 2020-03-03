package com.xudong.community.model;

/**
 * Created by xuxudong on 2020-03-02 17:22
 */
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long mgtCreate;
    private Long mgtModified;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMgtCreate(Long mgtCreate) {
        this.mgtCreate = mgtCreate;
    }

    public void setMgtModified(Long mgtModified) {
        this.mgtModified = mgtModified;
    }

    public Integer getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getToken() {
        return token;
    }

    public Long getMgtCreate() {
        return mgtCreate;
    }

    public Long getMgtModified() {
        return mgtModified;
    }
}
