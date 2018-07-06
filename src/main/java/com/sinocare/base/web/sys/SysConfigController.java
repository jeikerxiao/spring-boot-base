package com.sinocare.base.web.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinocare.base.core.annotation.SysLogger;
import com.sinocare.base.core.result.Result;
import com.sinocare.base.core.result.ResultUtil;
import com.sinocare.base.dto.common.IdVo;
import com.sinocare.base.dto.common.PageVo;
import com.sinocare.base.po.sys.SysConfig;
import com.sinocare.base.service.sys.SysConfigService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * SysConfig
 *
 * @version 2018/06/27
 * @author jeikerxiao
 */
@Api("配置管理")
@RestController
@RequestMapping("/sys/config")
public class SysConfigController {

    @Resource
    private SysConfigService sysConfigService;

    @PostMapping("/add")
    @RequiresPermissions("sys:config:add")
    public Result add(@RequestBody SysConfig sysConfig) {
        sysConfigService.save(sysConfig);
        return ResultUtil.success();
    }

    @SysLogger("删除配置")
    @PostMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public Result delete(@RequestBody IdVo idVo) {
        sysConfigService.deleteById(idVo.getId());
        return ResultUtil.success();
    }

    @SysLogger("修改配置")
    @PostMapping("/update")
    @RequiresPermissions("sys:config:update")
    public Result update(@RequestBody SysConfig sysConfig) {
        sysConfigService.update(sysConfig);
        return ResultUtil.success();
    }

    @PostMapping("/item")
    @RequiresPermissions("sys:config:item")
    public Result item(@RequestBody IdVo idVo) {
        SysConfig sysConfig = sysConfigService.findById(idVo.getId());
        return ResultUtil.success(sysConfig);
    }

    @PostMapping("/list")
    @RequiresPermissions("sys:config:list")
    public Result list(@RequestBody PageVo pageVo) {
        PageHelper.startPage(pageVo.getPage(), pageVo.getSize());
        List<SysConfig> list = sysConfigService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultUtil.success(pageInfo);
    }
}
