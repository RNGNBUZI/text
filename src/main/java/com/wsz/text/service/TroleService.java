package com.wsz.text.service;

import com.wsz.text.common.vo.PageVo;
import com.wsz.text.common.vo.ResultVo;
import com.wsz.text.entity.Trole;

import java.util.List;

public interface TroleService {

    List<Trole> queryAll ();

    ResultVo save(Trole trole);

    PageVo<Trole> queryPage(int page,int limit);

    ResultVo queryPerms(int id);

    ResultVo updateRole(int rid,String pids);

    ResultVo queryMenuPerm();
}
