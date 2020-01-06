package com.wsz.text.controller;

import com.wsz.text.common.util.ResultUtil;
import com.wsz.text.common.vo.PageVo;
import com.wsz.text.common.vo.ResultVo;
import com.wsz.text.entity.Trole;
import com.wsz.text.service.TroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TroleController {
    @Autowired
    private TroleService troleService;

    @GetMapping("roleall.do")
    public ResultVo all() {
        return ResultUtil.exec(true,"ok",troleService.queryAll());
    }
    @PostMapping("roleadd.do")
    public ResultVo save(Trole trole){
        return troleService.save(trole);
    }

    @GetMapping("rolepage.do")
    public PageVo<Trole> quryPage(int page,int limit) {

            return troleService.queryPage(page,limit);
    }

    @GetMapping("roleallperms.do")
    public ResultVo queryAll() {
        return troleService.queryMenuPerm();
    }

    @GetMapping("roleperms.do")
    public ResultVo queryPerms(int id) {
        return troleService.queryPerms(id);
    }

    @PostMapping("roleupdate.do")
    public ResultVo update(int rid,String pids) {
        return troleService.updateRole(rid,pids);
    }
}
