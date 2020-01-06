package com.wsz.text.common.vo;

import java.util.List;

/**
 *@Author feri
 *@Date Created in 2019/3/7 14:49
 */
public class EasyUIMenuVo {
    private int id;
    private String text;
    private boolean state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<EasyUIMenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<EasyUIMenuVo> children) {
        this.children = children;
    }

    private List<EasyUIMenuVo> children;

}
