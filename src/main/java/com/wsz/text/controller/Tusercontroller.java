package com.wsz.text.controller;


import com.wsz.text.common.sysconst.SystemCon;
import com.wsz.text.common.vo.PageVo;
import com.wsz.text.common.vo.ResultVo;
import com.wsz.text.entity.Tuser;
import com.wsz.text.entity.Tuserrole;
import com.wsz.text.service.TuserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class Tusercontroller {

    @Autowired
    private TuserService tuserService;

    @PostMapping("login.do")
    public ResultVo login( @RequestParam("name") String name, @RequestParam("password")String password,HttpServletRequest request) {
        ResultVo resultVo =tuserService.login(name, password);
        if (resultVo.getCode() == SystemCon.OK){
            HttpSession session = request.getSession();
            session.setAttribute("name",name);
            //需要告知Shiro
            //1.创建登录令牌
            UsernamePasswordToken token = new UsernamePasswordToken(name,password);
            //2.获取主题
            Subject subject = SecurityUtils.getSubject();
            //Session设置当前的登录用户
            subject.getSession().setAttribute("user",resultVo.getData());

            //4.发起认证 调用Shiro对应的Realm的认证方法
            subject.login(token);
        }

        return resultVo;
    }

    @GetMapping("detail.do")
    public ResultVo queryById(int id) {
        return tuserService.queryById(id);
    }

    @GetMapping("my.do")
    public ResultVo queryMy(Tuser tuser) {

        return tuserService.queryMy(tuser);

    }
    @GetMapping("like.do")
    public ResultVo queryByLike(String msg) {
        return tuserService.queryLike(msg);
    }

    @GetMapping("page.do")
    public PageVo<Tuser> page(Integer page, int limit) {
        return tuserService.queryPage(page, limit);
    }

    @GetMapping("pagelike.do")
    public PageVo<Tuser> page2(@RequestParam(defaultValue ="" )String msg,Integer page, int limit) {
        return tuserService.queryPage2(msg, page, limit);
    }

    @PostMapping("add.do")
    public ResultVo save(Tuser tuser, int rid) {
        return tuserService.save(tuser,rid);
    }

    @PostMapping("userdel.do")
    public ResultVo del(int id) {
        return tuserService.delete(id);
    }

    @PostMapping("userudpaterole.do")
    public ResultVo update(Integer id,int[] rids) {
        return tuserService.updateUr(id, rids);
    }

    @PostMapping("updaterole.do")
    public ResultVo updateById(Tuserrole tuserrole) {
        return tuserService.updateById(tuserrole);
    }
    @GetMapping("usermenu.do")
    public ResultVo getMenu() {
        Tuser tuser = (Tuser) SecurityUtils.getSubject().getSession().getAttribute("user");
        return tuserService.queryMenu(tuser.getId());
    }

    @GetMapping("userall.do")
    public ResultVo all(){
        return tuserService.queryAll();
    }

    @GetMapping("userrole.do")
    public ResultVo queryur(int id) {
        return tuserService.queryUr(id);
    }


}
