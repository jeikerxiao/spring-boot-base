package com.sinocare.base.core.oauth2;


import org.apache.shiro.authc.AuthenticationToken;

/**
 * Description: 鉴权 token
 * Created by jeikerxiao on 2018/6/27 下午4:46
 */
public class OAuth2Token implements AuthenticationToken {

    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
