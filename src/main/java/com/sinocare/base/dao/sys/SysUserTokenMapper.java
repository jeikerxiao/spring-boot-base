package com.sinocare.base.dao.sys;

import com.sinocare.base.core.MyMapper;
import com.sinocare.base.po.sys.SysUserToken;

public interface SysUserTokenMapper extends MyMapper<SysUserToken> {

    SysUserToken queryByToken(String token);

}