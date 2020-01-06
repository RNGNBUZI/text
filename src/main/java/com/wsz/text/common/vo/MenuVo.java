package com.wsz.text.common.vo;

import java.util.List;

/**
 *@Author feri
 *@Date Created in 2019/3/6 10:15
 */
public class MenuVo {
    private Integer id;
    private String name;
    private String perms;
    private String icon;
    private List<MenuVo> childs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuVo> getChilds() {
        return childs;
    }

    public void setChilds(List<MenuVo> childs) {
        this.childs = childs;
    }
}
