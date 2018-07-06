package com.sinocare.base.service.sys;

import com.sinocare.base.po.sys.SysUser;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Description: spring-boot-base
 * Created by jeikerxiao on 2018/6/28 下午4:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void queryByUserName() {
        SysUser user = sysUserService.queryByUserName("admin");
        assertEquals(user.getUsername(), "admin");
    }

    @Test
    public void updatePasswordTrue() {

        //sha256加密
        String password = new Sha256Hash("admin", "YzcmCZNvbXocrsz9dm8e").toHex();
        //sha256加密
        String newPassword = new Sha256Hash("admin", "YzcmCZNvbXocrsz9dm8e").toHex();
        boolean result = sysUserService.updatePassword(1L, password, newPassword);
        assertTrue(result);
    }

    @Test
    public void updatePasswordFalse() {

        //sha256加密
        String password = new Sha256Hash("admin", "YzcmCZNvbXocrsz9dm8").toHex();
        //sha256加密
        String newPassword = new Sha256Hash("admin", "YzcmCZNvbXocrsz9dm8e").toHex();
        boolean result = sysUserService.updatePassword(1L, password, newPassword);
        assertFalse(result);
    }
}