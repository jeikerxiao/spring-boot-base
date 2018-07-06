package com.sinocare.base.web.sys;

import com.sinocare.base.po.sys.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * Description: spring-boot-base
 * Created by jeikerxiao on 2018/6/27 下午4:57
 */
public abstract class AbstractController {

    protected SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }
}
