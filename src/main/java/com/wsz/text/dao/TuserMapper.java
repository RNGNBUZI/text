package com.wsz.text.dao;

import com.wsz.text.common.vo.UserRoleVo;
import com.wsz.text.entity.Tpermission;
import com.wsz.text.entity.Tuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TuserMapper extends BaseDao<Tuser>{
    int deleteByPrimaryKey(Integer id);

    int insert(Tuser tuser);

    int update(Tuser tuser);
    Tuser selectById(Integer id);
    //删除更改flag
    int updateByIdFlag(@Param("id") int id,@Param("flag")String flag);

    //登录
    Tuser selectByName(String name);
    //通过手机判断是否用户存在
    Tuser selectByPhone(String phone);
    //分页
    List<Tuser> queryByPage (@Param("index") int index,@Param("count")int count);

    List<Tuser> querByPage1 (@Param("msg")String msg, @Param("index") int index,@Param("count") int count);

    Tuser selectName(Tuser tuser);
    //查询数量
    long queryCount();

    //模糊查询
    List<Tuser> queryByLike(String msg);

    //查询用户的所有权限
    List<String> selectByUidPerms(Integer uid);
    //查询用户的所有菜单
    List<Tpermission> selectByUidMenu(Integer uid);

    List<Tuser> selectAll();

    List<UserRoleVo> selectByUidRole();

    List<Integer> selectByUr(int id);

    void insertMultiInfo(List<Tuser> list);
}