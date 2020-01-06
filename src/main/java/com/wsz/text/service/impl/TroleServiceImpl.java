package com.wsz.text.service.impl;

import com.wsz.text.common.util.ResultUtil;
import com.wsz.text.common.vo.EasyUIMenuVo;
import com.wsz.text.common.vo.PageVo;
import com.wsz.text.common.vo.ResultVo;
import com.wsz.text.common.vo.RolePermVo;
import com.wsz.text.dao.TpermissionMapper;
import com.wsz.text.dao.TroleMapper;
import com.wsz.text.dao.TrolepermissionMapper;
import com.wsz.text.entity.Tpermission;
import com.wsz.text.entity.Trole;
import com.wsz.text.entity.Trolepermission;
import com.wsz.text.service.TroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class TroleServiceImpl implements TroleService {
    @Autowired
    private TroleMapper troleMapper;
    @Autowired
    private TpermissionMapper tpermissionmapper;
    @Autowired
    private TrolepermissionMapper trolepermissionmapper;

    @Override
    public List<Trole> queryAll() {

        return troleMapper.selectAll();
    }

    @Override
    public ResultVo save(Trole trole) {
        return ResultUtil.exec(troleMapper.insert(trole) > 0, "OK", null);
    }

    @Override
    public PageVo<Trole> queryPage(int page, int limit) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("index", (page - 1) * limit);
        map.put("count", limit);
        return ResultUtil.exec(page, limit, troleMapper.selectCount(), troleMapper.selectPage(map));
    }

    @Override
    public ResultVo queryPerms(int id) {
        List<Tpermission> tpermissions = troleMapper.selectAllPerms(id);
        HashMap<String,ArrayList<Integer>> map = new HashMap<>();
        ArrayList<Integer> mids = new ArrayList<>();
        ArrayList<Integer> pids = new ArrayList<>();
        for (Tpermission p: tpermissions) {
            if (p.getType() == 1) {
                mids.add(p.getId());
            } else if (p.getType() == 2) {
                pids.add(p.getId());
            }
        }
        map.put("mids",mids);
        map.put("pids",pids);
        return ResultUtil.exec(true,"OK",map);
    }

    @Override
    public ResultVo updateRole(int rid, String pids) {
        //删除原来的权限
        trolepermissionmapper.deleteByRId(rid);
        //新增这次的权限
        String[] perms = pids.split(",");
        for (String p : perms) {
            Trolepermission trolepermission = new Trolepermission();
            trolepermission.setPid(Integer.parseInt(p));
            trolepermission.setRid(rid);
            trolepermissionmapper.insert(trolepermission);
        }
        return ResultUtil.exec(true,"OK",null);
    }

    @Override
    public ResultVo queryMenuPerm() {
        List<Tpermission> tpermissions = tpermissionmapper.selectAllPerms(1);
        List<EasyUIMenuVo> rootMenu = new ArrayList<>();
        for (Tpermission p : tpermissions) {
            if (p.getLevel() == 1) {
                EasyUIMenuVo menuVo = new EasyUIMenuVo();
                menuVo.setId(p.getId());
                menuVo.setText(p.getName());
                menuVo.setState(true);
                menuVo.setChildren(new ArrayList<>());
                rootMenu.add(menuVo);
            } else if (p.getLevel() == 2) {
                //二级菜单
                EasyUIMenuVo menuVoParnet = getParent(p.getParentid(), rootMenu);
                if (menuVoParnet != null) {
                    //找到了对应的一级菜单
                    EasyUIMenuVo childMenu = new EasyUIMenuVo();
                    childMenu.setId(p.getId());
                    childMenu.setText(p.getName());
                    childMenu.setState(true);
                    menuVoParnet.getChildren().add(childMenu);
                }
            }
        }
        List<Tpermission> perms = tpermissionmapper.selectAllPerms(2);

        RolePermVo rolePermVo = new RolePermVo();
        rolePermVo.setMenus(rootMenu);
        rolePermVo.setPerms(perms);
        return ResultUtil.exec(true, "OK", rolePermVo);
    }

    private EasyUIMenuVo getParent(int id,List<EasyUIMenuVo> menuVos){
        for(EasyUIMenuVo m:menuVos){
            if(m.getId()==id){
                return m;
            }
        }
        return null;
    }

}