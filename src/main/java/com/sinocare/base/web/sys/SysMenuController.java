package com.sinocare.base.web.sys;

import com.sinocare.base.core.annotation.SysLogger;
import com.sinocare.base.core.result.Result;
import com.sinocare.base.core.result.ResultUtil;
import com.sinocare.base.po.sys.SysMenu;
import com.sinocare.base.service.sys.ShiroService;
import com.sinocare.base.service.sys.SysMenuService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * SysMenu 菜单接口
 *
 * @version 2018/06/27
 * @author jeikerxiao
 */
@Api("菜单管理")
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {

    @Resource
    private SysMenuService sysMenuService;
    @Autowired
    private ShiroService shiroService;

    @SysLogger("保存菜单")
    @PostMapping("/add")
    @RequiresPermissions("sys:menu:add")
    public Result add(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return ResultUtil.success();
    }

    @SysLogger("删除菜单")
    @PostMapping("/delete/{menuId}")
    @RequiresPermissions("sys:menu:delete")
    public Result delete(@PathVariable("menuId") Long menuId) {
        sysMenuService.deleteById(menuId);
        return ResultUtil.success();
    }

    @SysLogger("修改菜单")
    @PostMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public Result update(@RequestBody SysMenu sysMenu) {
        sysMenuService.update(sysMenu);
        return ResultUtil.success();
    }

    @GetMapping("/item/{menuId}")
    @RequiresPermissions("sys:menu:item")
    public Result item(@PathVariable("menuId") Long menuId) {
        SysMenu sysMenu = sysMenuService.findById(menuId);
        return ResultUtil.success(sysMenu);
    }

    @PostMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public Result list() {
        List<SysMenu> menuList = sysMenuService.findAll();
        for(SysMenu menu : menuList){
            SysMenu parentMenu = sysMenuService.findById(menu.getParentId());
            if(parentMenu != null){
                menu.setParentName(parentMenu.getName());
            }
        }
        return ResultUtil.success(menuList);
    }

    /**
     * 导航菜单
     */
    @GetMapping("/nav")
    public Result nav(){
        List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        Map<String, Object> result = new HashMap<>();
        result.put("menuList", menuList);
        result.put("permissions", permissions);
        return ResultUtil.success(result);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public Result select(){
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenu root = new SysMenu();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return ResultUtil.success(menuList);
    }
}
