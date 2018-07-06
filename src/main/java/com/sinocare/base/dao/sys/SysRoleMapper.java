package com.sinocare.base.dao.sys;

import com.sinocare.base.core.MyMapper;
import com.sinocare.base.po.sys.SysRole;

import java.util.List;

public interface SysRoleMapper extends MyMapper<SysRole> {

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}