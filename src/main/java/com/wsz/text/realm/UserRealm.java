package com.wsz.text.realm;

import com.wsz.text.entity.Tuser;
import com.wsz.text.service.TuserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private TuserService tuserService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       //1.获取登录户
        Tuser user = (Tuser) SecurityUtils.getSubject().getSession().getAttribute("user");
        // 2.查询对应的权限
        List<String> perms = tuserService.queryPerms(user.getId());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //设置权限到指定对象中
        authorizationInfo.addStringPermissions(perms);
        return authorizationInfo;
    }

    //认证 登录许可 标记是否登陆成功
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取传递的令牌
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        //验证令牌是否为空
        if(userToken.getUsername() != null) {

            //创建认证信息 参数说明:1.用户名，2.密码。3.realm的名字
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userToken.getUsername(),userToken.getPassword(),getName());

            return authenticationInfo;
        }
        return null;
    }
}
