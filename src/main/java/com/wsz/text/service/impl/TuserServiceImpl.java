package com.wsz.text.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsz.text.common.util.ResultUtil;
import com.wsz.text.common.vo.PageVo;
import com.wsz.text.common.vo.ResultVo;
import com.wsz.text.dao.TuserMapper;
import com.wsz.text.dao.TuserroleMapper;
import com.wsz.text.entity.Tuser;
import com.wsz.text.entity.Tuserrole;
import com.wsz.text.service.TuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TuserServiceImpl implements TuserService {
    @Autowired
    private TuserMapper tuserMapper;
    private TuserroleMapper tuserroleMapper;
    @Override
    public ResultVo login(String name, String password) {
        Tuser tuser = tuserMapper.selectByName(name);
        if (tuser != null) {
            if (tuser.getPassword().equals(password)) {
                //登陆成功
                return ResultUtil.exec(true,"OK",tuser);
            }
        }
        return ResultUtil.exec(false,"用户名或者密码不正确",null);
    }

    @Override
    public ResultVo queryById(Integer id) {
        Tuser tuser = tuserMapper.selectById(id);
        return ResultUtil.exec(tuser!=null,"查询详细",tuser);
    }

    @Override
    public ResultVo queryLike(String msg) {
        msg ='%' + msg +'%';
        return ResultUtil.exec(true,"",tuserMapper.queryByLike(msg));
    }

    @Override
    public PageVo<Tuser> queryPage(Integer page, Integer size) {
        return ResultUtil.exec(page,size,tuserMapper.queryCount(),tuserMapper.queryByPage((page-1)*size,size));
    }

    @Override
    public PageVo<Tuser> queryPage2(String msg, Integer page, Integer size) {
        msg='%'+msg+'%';
        return ResultUtil.exec(page,size,tuserMapper.queryCount(),tuserMapper.querByPage1(msg,(page-1)*size,size));
    }

    @Override
    @Transactional
    public ResultVo save(Tuser tuser, int rid) {
        tuserMapper.insert(tuser);
        //新增用户角色
         Tuserrole tuserRole=new Tuserrole();
        tuserRole.setRid(rid);
        tuserRole.setUid(tuser.getId());
        tuserroleMapper.insert(tuserRole);
        return ResultUtil.exec(true,"OK",null);
    }
}

