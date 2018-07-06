package com.sinocare.base.service.sys;

import com.sinocare.base.core.Service;
import com.sinocare.base.po.sys.SysMenu;

import java.util.List;


/**
 * SysMenu
 *
 * @version 2018/06/27
 * @author jeikerxiao
 */
public interface SysMenuService extends Service<SysMenu> {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<SysMenu> getUserMenuList(Long userId);
}
