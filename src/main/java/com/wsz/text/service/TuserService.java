package com.wsz.text.service;

import com.wsz.text.common.vo.PagePermissionVo;
import com.wsz.text.common.vo.PageVo;
import com.wsz.text.common.vo.ResultVo;
import com.wsz.text.entity.Tuser;
import com.wsz.text.entity.Tuserrole;

import java.util.List;

public interface TuserService {

        //登录
        ResultVo login(String name,String password);

        Tuser selectByName(String name);

        ResultVo queryById(Integer id);
        //模糊查询
        ResultVo queryLike(String msg);
        //查询个人信息
        ResultVo queryMy(Tuser tuser);

        //分页
        PageVo<Tuser> queryPage(Integer page, Integer size);
        //模糊查询分页
        PageVo<Tuser> queryPage2(String msg,Integer page,Integer size);
        //新增
        ResultVo save(Tuser tuser, int rid);
        //删除
        ResultVo delete(int uid);

        ResultVo update(Tuser record);

        ResultVo updateUr(int uid, int[] rids);

        ResultVo updateById(Tuserrole tuserrole);

        List<String> queryPerms(Integer uid);

        ResultVo queryUr(int id);

        ResultVo changeRole(int uid, int[] rids);

        ResultVo queryMenu (Integer uid);

        ResultVo queryAll();

        PagePermissionVo<Tuser> queryPagePer(int page, int count);



}
