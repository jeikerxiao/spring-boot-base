package com.sinocare.base.dao.sys;

import com.sinocare.base.core.MyMapper;
import com.sinocare.base.po.sys.SysMenu;

import java.util.List;

public interface SysMenuMapper extends MyMapper<SysMenu> {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();
}