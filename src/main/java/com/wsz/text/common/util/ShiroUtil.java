package com.wsz.text.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 *@Author feri
 *@Date Created in 2019/3/7 10:19
 */
public class ShiroUtil {
    public static boolean checkPermission(String perm){
        Subject subject=SecurityUtils.getSubject();
        return  subject.isPermitted(perm);
    }
}