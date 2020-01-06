package com.wsz.text.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 *@Author feri
 *@Date Created in 2019/3/6 15:22
 */
public class UserVo {
    private Integer id;
    private String name;
    private String roles;
    private String flagmsg;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

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

    public String getRoles(String roles) {
        return this.roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getFlagmsg() {
        return flagmsg;
    }

    public void setFlagmsg(String flagmsg) {
        this.flagmsg = flagmsg;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
