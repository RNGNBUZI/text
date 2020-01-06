package com.wsz.text.common.vo;


import com.wsz.text.entity.Tpermission;

import java.util.List;

/**
 *@Author feri
 *@Date Created in 2019/3/7 15:00
 */
public class RolePermVo {
    private List<EasyUIMenuVo> menus;
    private List<Tpermission> perms;

    public List<EasyUIMenuVo> getMenus() {
        return menus;
    }

    public void setMenus(List<EasyUIMenuVo> menus) {
        this.menus = menus;
    }

    public List<Tpermission> getPerms() {
        return perms;
    }

    public void setPerms(List<Tpermission> perms) {
        this.perms = perms;
    }
}
