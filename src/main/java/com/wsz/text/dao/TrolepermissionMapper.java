package com.wsz.text.dao;

import com.wsz.text.entity.Tpermission;
import com.wsz.text.entity.Trolepermission;

import java.util.List;

public interface TrolepermissionMapper {
    int deleteById(Integer id);

    int deleteByRId(Integer rid);

    int insert(Trolepermission record);

    List<Tpermission> selectByRid(Integer rid);
}