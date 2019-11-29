package com.wsz.text.dao;

import com.wsz.text.entity.Tuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TuserMapper extends BaseDao<Tuser>{
    int deleteByPrimaryKey(Integer id);

    int insert(Tuser tuser);

    int insertSelective(Tuser record);

    Tuser selectById(Integer id);

    int updateByPrimaryKeySelective(Tuser record);

    int updateByPrimaryKey(Tuser record);

    Tuser selectByName(String name);
    //分页
    List<Tuser> queryByPage (@Param("index") int index,@Param("count")int count);

    List<Tuser> querByPage1 (@Param("msg")String msg, @Param("index") int index,@Param("count") int count);

    //查询数量
    long queryCount();

    //模糊查询
    List<Tuser> queryByLike(String msg);
}