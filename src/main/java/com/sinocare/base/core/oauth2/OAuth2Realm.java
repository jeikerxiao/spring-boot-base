package com.sinocare.base.core.oauth2;


import com.sinocare.base.po.sys.SysUser;
import com.sinocare.base.po.sys.SysUserToken;
import com.sinocare.base.service.sys.ShiroService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Description: 授权 Realm
 * Created by jeikerxiao on 2018/6/27 下午4:46
 */
@Component
@Slf4j
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.debug("doGetAuthorizationInfo");

        SysUser user = (SysUser)principals.getPrimaryPrincipal();
        Long userId = user.getUserId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.debug("doGetAuthenticationInfo");

        String accessToken = (String) token.getPrincipal();

        // 根据Token，查询用户信息
        SysUserToken userToken = shiroService.queryByToken(accessToken);
        // token失效
        if(userToken == null || userToken.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效, 请重新登录");
        }

        // 查询用户信息
        SysUser user = shiroService.queryUser(userToken.getUserId());
        // 账号锁定
        if(user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定, 请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
