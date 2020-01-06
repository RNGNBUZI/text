package com.wsz.text.entity;

import java.util.List;

public class Trole {
    private Integer id;

    private String rname;

    private String info;

    public List<Tuser> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<Tuser> userInfos) {
        this.userInfos = userInfos;
    }

    private List<Tuser> userInfos;
    public List<Tpermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Tpermission> permissions) {
        this.permissions = permissions;
    }

    private List<Tpermission> permissions;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}