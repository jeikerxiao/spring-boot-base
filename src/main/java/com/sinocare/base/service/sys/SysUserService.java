package com.sinocare.base.service.sys;

import com.sinocare.base.core.Service;
import com.sinocare.base.po.sys.SysUser;

import java.util.List;


/**
 * SysUser
 *
 * @version 2018/06/27
 * @author jeikerxiao
 */
public interface SysUserService extends Service<SysUser> {

    /**
     * 新增用户
     */
    void add(SysUser user);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(SysUser user);

    /**
     * 批量删除
     */
    void deleteByIds(Long[] userIds);

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUser queryByUserName(String userName);

    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    boolean updatePassword(Long userId, String password, String newPassword);

}
