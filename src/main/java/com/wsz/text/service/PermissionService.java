package com.wsz.text.service;

import com.wsz.text.common.vo.PageVo;
import com.wsz.text.common.vo.ResultVo;
import com.wsz.text.entity.Tpermission;

public interface PermissionService {
    ResultVo save(Tpermission tpermission);
    PageVo<Tpermission> queryPage(int page, int limit);
    ResultVo queryRootMenu();
}
