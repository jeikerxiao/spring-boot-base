package com.sinocare.base.service.sys;

import com.sinocare.base.core.Service;
import com.sinocare.base.po.sys.SysUserRole;

import java.util.List;


/**
 * SysUserRole
 *
 * @version 2018/06/27
 * @author jeikerxiao
 */
public interface SysUserRoleService extends Service<SysUserRole> {

    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
