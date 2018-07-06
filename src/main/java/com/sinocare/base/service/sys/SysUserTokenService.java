package com.sinocare.base.service.sys;

import com.sinocare.base.core.Service;
import com.sinocare.base.po.sys.SysUserToken;


/**
 * SysUserToken
 *
 * @version 2018/06/27
 * @author jeikerxiao
 */
public interface SysUserTokenService extends Service<SysUserToken> {

    /**
     * 生成token
     * @param userId  用户ID
     */
    SysUserToken createToken(long userId);

    /**
     * 退出，修改token值
     * @param userId  用户ID
     */
    void logout(long userId);
}
