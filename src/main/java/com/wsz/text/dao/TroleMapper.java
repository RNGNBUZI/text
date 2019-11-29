package com.wsz.text.dao;

import com.wsz.text.entity.Trole;

import java.util.List;

public interface TroleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Trole record);

    int insertSelective(Trole record);

   int selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Trole record);

    int updateByPrimaryKey(Trole record);

    List<Trole> selectAll();
}