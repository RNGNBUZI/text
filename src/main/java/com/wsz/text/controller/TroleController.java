package com.wsz.text.controller;

import com.wsz.text.common.util.ResultUtil;
import com.wsz.text.common.vo.ResultVo;
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
}
