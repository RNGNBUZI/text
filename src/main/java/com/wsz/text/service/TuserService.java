package com.wsz.text.service;

import com.github.pagehelper.PageInfo;
import com.wsz.text.common.vo.PageVo;
import com.wsz.text.common.vo.ResultVo;
import com.wsz.text.entity.Tuser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TuserService {

    //登录
    ResultVo login(String name,String password);

    ResultVo queryById(Integer id);
    //模糊查询
    ResultVo queryLike(String msg);

    //分页
    PageVo<Tuser> queryPage(Integer page, Integer size);
    //模糊查询分页
    PageVo<Tuser> queryPage2(String msg,Integer page,Integer size);

    ResultVo save(Tuser tuser, int rid);
}
