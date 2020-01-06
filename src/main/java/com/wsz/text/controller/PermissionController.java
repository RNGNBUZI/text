package com.wsz.text.controller;

import com.wsz.text.common.util.ResultUtil;
import com.wsz.text.common.vo.PageVo;
import com.wsz.text.common.vo.ResultVo;
import com.wsz.text.entity.Tpermission;
import com.wsz.text.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.spring.boot.autoconfigure.ShiroAnnotationProcessorAutoConfiguration;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionController {
    ShiroAnnotationProcessorAutoConfiguration p;
    @Autowired
    private PermissionService permissionService;

    @GetMapping("permissioncheck.do")
    public ResultVo check(String permission) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isPermitted(permission)) {
            return ResultUtil.exec(true, "ok", null);
        } else {
            return ResultUtil.exec(false, "ERROR", null);
        }

    }

    @RequiresPermissions("add")
    @PostMapping("permissionadd.do")
    public ResultVo save(Tpermission tpermission) {
        return permissionService.save(tpermission);
    }

    @GetMapping("permissionpage.do")
    public PageVo<Tpermission> queryPage(int page, int limit) {
        return permissionService.queryPage(page, limit);

    }

    @GetMapping("permissionlevel.do")
    public ResultVo query(){
        return permissionService.queryRootMenu();
    }
}
