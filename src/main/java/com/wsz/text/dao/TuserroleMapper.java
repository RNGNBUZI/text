package com.wsz.text.dao;

import com.wsz.text.entity.Tuserrole;

public interface TuserroleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tuserrole tuserrole);

    int insertSelective(Tuserrole record);

    Tuserrole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tuserrole record);

    int updateByPrimaryKey(Tuserrole record);
}