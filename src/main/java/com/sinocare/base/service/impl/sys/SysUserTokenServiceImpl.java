package com.sinocare.base.service.impl.sys;

import com.sinocare.base.core.AbstractService;
import com.sinocare.base.core.oauth2.TokenGenerator;
import com.sinocare.base.dao.sys.SysUserTokenMapper;
import com.sinocare.base.po.sys.SysUserToken;
import com.sinocare.base.service.sys.SysUserTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * SysUserToken
 *
 * @version 2018/06/27
 * @author jeikerxiao
 */
@Service
@Transactional
public class SysUserTokenServiceImpl extends AbstractService<SysUserToken> implements SysUserTokenService {

    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Resource
    private SysUserTokenMapper sysUserTokenMapper;

    @Override
    public SysUserToken createToken(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        SysUserToken userToken = sysUserTokenMapper.selectByPrimaryKey(userId);
        if(userToken == null){
            userToken = new SysUserToken();
            userToken.setUserId(userId);
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);
            //保存token
            sysUserTokenMapper.insert(userToken);
        }else{
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);
            //更新token
            sysUserTokenMapper.updateByPrimaryKeySelective(userToken);
        }
        return userToken;
    }

    @Override
    public void logout(long userId) {
        // 生成一个token
        String token = TokenGenerator.generateValue();

        // 修改token
        SysUserToken userToken = new SysUserToken();
        userToken.setUserId(userId);
        userToken.setToken(token);
        sysUserTokenMapper.updateByPrimaryKeySelective(userToken);
    }
}
