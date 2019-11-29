package com.wsz.text.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wsz.text.common.util.ResultUtil;
import com.wsz.text.common.vo.PageVo;
import com.wsz.text.common.vo.ResultVo;
import com.wsz.text.entity.Tuser;
import com.wsz.text.service.TuserService;
import org.hibernate.validator.internal.util.logging.Log_$logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Tusercontroller {

    @Autowired
    private TuserService tuserService;

    @PostMapping("login.do")
    public ResultVo login(String name, String password) {
        ResultVo resultVo =tuserService.login(name, password);
        return resultVo;
    }

    @GetMapping("detail.do")
    public ResultVo queryById(int id) {
        return tuserService.queryById(id);
    }

    @GetMapping("like.do")
    public ResultVo queryByLike(String msg) {
        return tuserService.queryLike(msg);
    }

    @GetMapping("page.do")
    public PageVo<Tuser> page(Integer page, int limit) {
        return tuserService.queryPage(page, limit);
    }

    @GetMapping("pagelike.do")
    public PageVo<Tuser> page2(@RequestParam(defaultValue ="" )String msg,Integer page, int limit) {
        return tuserService.queryPage2(msg, page, limit);
    }

    @PostMapping("add.do")
    public ResultVo save(Tuser tuser, int rid) {
        return tuserService.save(tuser,rid);
    }
}
