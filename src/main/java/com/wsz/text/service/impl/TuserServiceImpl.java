package com.wsz.text.service.impl;


import com.wsz.text.common.util.ResultUtil;
import com.wsz.text.common.util.ShiroUtil;
import com.wsz.text.common.vo.*;
import com.wsz.text.dao.TuserMapper;
import com.wsz.text.dao.TuserroleMapper;
import com.wsz.text.entity.Tpermission;
import com.wsz.text.entity.Tuser;
import com.wsz.text.entity.Tuserrole;
import com.wsz.text.service.TuserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class TuserServiceImpl implements TuserService {
    @Autowired
    private TuserMapper tuserMapper;

    @Autowired
    private TuserroleMapper tuserroleMapper;
    @Override
    public ResultVo login(String name, String password) {

        Tuser tuser = tuserMapper.selectByName(name);
        if (tuser == null) {
            return ResultUtil.exec(false,"用户名错误或不存在",null);
        }

        if (tuser.getFlag().equals("禁用")) {
            return ResultUtil.exec(false,"用户名冻结禁用",null);
        }
        if (!tuser.getPassword().equals(password)){
            return ResultUtil.exec(false,"密码错误",null);
        }

        return ResultUtil.exec(true,"ok",tuser);
    }

    @Override
    public Tuser selectByName(String name) {
        return tuserMapper.selectByName(name);
    }

    @Override
    public ResultVo queryById(Integer id) {
        Tuser tuser = tuserMapper.selectById(id);
        return ResultUtil.exec(tuser!=null,"查询详细",tuser);
    }

    @Override
    public ResultVo queryLike(String msg) {
        msg ='%' + msg +'%';
        return ResultUtil.exec(true,"",tuserMapper.queryByLike(msg));
    }

    @Override
    public ResultVo queryMy(Tuser tuser) {
        return ResultUtil.exec(true,"查询详细",tuserMapper.selectName(tuser));
    }

    @Override
    public PageVo<Tuser> queryPage(Integer page, Integer size) {
        return ResultUtil.exec(page,size,tuserMapper.queryCount(),tuserMapper.queryByPage((page-1)*size,size));
    }

    @Override
    public PageVo<Tuser> queryPage2(String msg, Integer page, Integer size) {
        msg='%'+msg+'%';
        return ResultUtil.exec(page,size,tuserMapper.queryCount(),tuserMapper.querByPage1(msg,(page-1)*size,size));
    }

    @Override
    @Transactional
    public ResultVo save(Tuser tuser, int rid) {
        //判断用户是否存在
        Tuser tuser1 = tuserMapper.selectByPhone(tuser.getPhone());
        if (tuser1 == null) {

            tuserMapper.insert(tuser);
            //新增用户角色
            Tuserrole tuserRole = new Tuserrole();
            tuserRole.setRid(rid);
            tuserRole.setUid(tuser.getId());
            tuserroleMapper.insert(tuserRole);

            return ResultUtil.exec(true, "OK", null);
        }
        return ResultUtil.exec(false,"用户已存在",null);
    }

    @Override
    public ResultVo delete(int uid) {
        return ResultUtil.exec(tuserMapper.updateByIdFlag(uid,"禁用")> 0 ,"ok",null);
    }

    @Override
    public ResultVo update(Tuser record) {
        return ResultUtil.exec(true,"OK",null);
    }

    @Override
    @Transactional
    public ResultVo updateUr(int uid, int[] rids) {
        int i = tuserroleMapper.deleteByUid(uid);
        int i1 = tuserroleMapper.insertBatch(uid, rids);
        return ResultUtil.exec(true,"OK",null);
    }

    @Override
    public ResultVo updateById(Tuserrole tuserrole) {
        return ResultUtil.exec(true,"ok",tuserroleMapper.updateById(tuserrole));
    }

    @Override
    public List<String> queryPerms(Integer uid) {
        return tuserMapper.selectByUidPerms(uid);
    }

    @Override
    public ResultVo queryUr(int id) {
        return ResultUtil.exec(true,"ok",tuserMapper.selectByUr(id));
    }

    @Override
    public ResultVo changeRole(int uid, int[] rids) {
        //先删除原来的
        tuserroleMapper.deleteByUid(uid);
        for (Integer rid:rids) {
            Tuserrole tuserrole = new Tuserrole();
            tuserrole.setRid(rid);
            tuserrole.setUid(uid);
            tuserroleMapper.insert(tuserrole);
        }
        tuserroleMapper.insertBatch(uid,rids);
        return ResultUtil.exec(true,"ok",null);
    }

    @Override
    public ResultVo queryMenu(Integer uid) {
        List<Tpermission> tpermissions = tuserMapper.selectByUidMenu(uid);
        List<MenuVo> rootMenu = new ArrayList<>();
        for (Tpermission p: tpermissions) {
            if (p.getLevel() == 1) {
                MenuVo menuVo = new MenuVo();
                BeanUtils.copyProperties(p,menuVo);
                menuVo.setChilds(new ArrayList<>());
                rootMenu.add(menuVo);
            } else if (p.getLevel() == 2) {
                //二级菜单
                MenuVo menuVoParent = getParent(p.getParentid(),rootMenu);
                if (menuVoParent != null) {
                    //找到对应的一级菜单
                    MenuVo childMenu = new MenuVo();
                    BeanUtils.copyProperties(p,childMenu);
                    menuVoParent.getChilds().add(childMenu);
                }
            }
        }
        return ResultUtil.exec(true,"ok",rootMenu);
    }

    @Override
    public ResultVo queryAll() {
        List<Tuser> list = tuserMapper.selectAll();
        List<UserRoleVo> userRoleVoList = tuserMapper.selectByUidRole();
        List<UserVo> userVos = new ArrayList<>();
        for (Tuser tuser: list) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(tuser,userVo);
            userVo.setFlagmsg(getFlag(tuser.getFlag()));
            userVo.getRoles(getRoles(userRoleVoList,tuser.getId()));
            userVos.add(userVo);
        }
        return ResultUtil.exec(true,"OK",userVos);
    }

    @Override
    public PagePermissionVo<Tuser> queryPagePer(int page, int count) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("index",(page-1)*count);
        map.put("count",count);
        return ResultUtil.exec(page,count,tuserMapper.selectCount(),
                ShiroUtil.checkPermission("user:update"), ShiroUtil.checkPermission("user:del"),
                tuserMapper.selectPage(map));
    }

    private MenuVo getParent(int id,List<MenuVo> menuVos) {
        for (MenuVo m:menuVos) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }
    private String getFlag(String flag){
        String res="";
        if (flag == "可用") {
            res="有效";
        } else if (flag == "临时可用") {
            res="冻结";
        } else if (flag == "不可用") {
            res = "无效";
        }
        return res;
    }

    private String getRoles(List<UserRoleVo> list,Integer uid){
        StringBuffer buffer=new StringBuffer();
        for(UserRoleVo userRoleVo:list){
            if(userRoleVo.getUid().equals(uid)){
                buffer.append(userRoleVo.getName()+",");
            }
        }
        if(buffer.lastIndexOf(",")>0){
            buffer.deleteCharAt(buffer.length()-1);
        }
        return buffer.toString();
    }
}

