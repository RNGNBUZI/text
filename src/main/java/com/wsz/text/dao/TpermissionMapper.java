package com.wsz.text.dao;

import com.wsz.text.entity.Tpermission;

import java.util.List;

public interface TpermissionMapper extends BaseDao<Tpermission>{

    int deleteById (Integer id);

    Tpermission selectById(Integer id);

    int updateByIdSelective(Tpermission record);

    List<Tpermission> selectByLevel(int level);

    List<Tpermission> selectAllPerms(int type);
}