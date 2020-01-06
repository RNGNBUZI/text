package com.wsz.text.common.vo;

/**
 *@Author feri
 *@Date Created in 2019/3/7 10:15
 */
public class PagePermissionVo<T> extends PageVo<T> {
    private boolean edit;
    private boolean del;

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }
}
