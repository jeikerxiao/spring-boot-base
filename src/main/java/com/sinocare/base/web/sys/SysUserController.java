package com.sinocare.base.web.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinocare.base.core.annotation.SysLogger;
import com.sinocare.base.core.common.MessageSourceService;
import com.sinocare.base.core.result.Result;
import com.sinocare.base.core.result.ResultCode;
import com.sinocare.base.core.result.ResultUtil;
import com.sinocare.base.dto.common.PageVo;
import com.sinocare.base.dto.user.PasswordDto;
import com.sinocare.base.po.sys.SysUser;
import com.sinocare.base.service.sys.SysUserRoleService;
import com.sinocare.base.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * SysUser
 *
 * @author jeikerxiao
 * @version 2018/06/27
 */
@Api(description = "用户管理")
@RestController
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController extends AbstractController {

    @Resource
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private MessageSourceService messageSourceService;

    @SysLogger("保存用户")
    @PostMapping("/add")
    @RequiresPermissions("sys:user:add")
    public Result add(@Valid @RequestBody SysUser sysUser) {
        sysUser.setCreateUserId(getUserId());
        sysUserService.add(sysUser);
        return ResultUtil.success();
    }

    @SysLogger("删除用户")
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public Result delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return ResultUtil.failure("系统管理员不能删除");
        }
        if (ArrayUtils.contains(userIds, getUserId())) {
            return ResultUtil.failure("当前用户不能删除");
        }
        sysUserService.deleteByIds(userIds);
        return ResultUtil.success();
    }

    @SysLogger("修改用户")
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public Result update(@Valid @RequestBody SysUser sysUser) {
        sysUserService.update(sysUser);
        return ResultUtil.success();
    }

    @GetMapping("/item/{userId}")
    @RequiresPermissions("sys:user:item")
    public Result item(@PathVariable("userId") Long userId) {
        SysUser sysUser = sysUserService.findById(userId);
        return ResultUtil.success(sysUser);
    }

    @PostMapping("/list")
    @RequiresPermissions("sys:user:list")
    public Result list(@RequestBody PageVo pageVo) {
        PageHelper.startPage(pageVo.getPage(), pageVo.getSize());
        List<SysUser> list = sysUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public Result info() {
        return ResultUtil.success(getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLogger("修改密码")
    @PostMapping("/password")
    public Result password(@RequestBody PasswordDto passwordDto) {
        //sha256加密
        String password = new Sha256Hash(passwordDto.getPassword(), getUser().getSalt()).toHex();
        //sha256加密
        String newPassword = new Sha256Hash(passwordDto.getNewPassword(), getUser().getSalt()).toHex();
        //更新密码
        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return ResultUtil.failure(ResultCode.USER_OLDPASSWORD_ERROR);
        }
        return ResultUtil.success();
    }

    @ApiOperation("测试国际化")
    @GetMapping("/test")
    public Result test() {
        String msg = messageSourceService.getMessage("hello.world");
        log.info("hello.world = {}", msg);
        return ResultUtil.success(msg);
    }
}
