package com.wsz.text.dao;

import com.wsz.text.entity.Trole;
import com.wsz.text.entity.Tuserrole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TuserroleMapper {
    int deleteById(Integer id);
    //新增
    int insert(Tuserrole tuserrole);

    int updateById(Tuserrole tuserrole);

    int deleteByUid(Integer uid);

    int insertBatch(@Param("uid")Integer uid,@Param("rids") int[] rids);

    List<Trole> selectByUid(Integer uid);
}