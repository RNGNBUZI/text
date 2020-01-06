package com.wsz.text.service.impl;

import com.wsz.text.common.util.ResultUtil;
import com.wsz.text.common.vo.PageVo;
import com.wsz.text.common.vo.ResultVo;
import com.wsz.text.dao.TpermissionMapper;
import com.wsz.text.entity.Tpermission;
import com.wsz.text.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private TpermissionMapper tpermissionMapper;

    @Override
    public ResultVo save(Tpermission tpermission) {
        if (tpermission.getType() == 2) {
            tpermission.setIcon(null);
            tpermission.setLevel(null);
            tpermission.setParentid(null);
        }

        return ResultUtil.exec(tpermissionMapper.insert(tpermission)>0,"OK",null);
    }

    @Override
    public PageVo<Tpermission> queryPage(int page, int limit) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("index",(page-1)*limit);
        map.put("count",limit);
        return ResultUtil.exec(page,limit,tpermissionMapper.selectCount(),tpermissionMapper.selectPage(map));
    }

    @Override
    public ResultVo queryRootMenu() {
        return ResultUtil.exec(true,"OK",tpermissionMapper.selectByLevel(1));
    }
}
