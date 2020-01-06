package com.wsz.text.dao;

import com.wsz.text.entity.Tpermission;
import com.wsz.text.entity.Trole;

import java.util.List;

public interface TroleMapper extends BaseDao<Trole>{
    int deleteById(Integer id);

    Trole selectById(Integer id);

    int updateById(Trole record);

    List<Trole> selectAll();

    List<Tpermission> selectAllPerms(int id);
}