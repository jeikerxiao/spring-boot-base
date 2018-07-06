package com.sinocare.base.dao.sys;

import com.sinocare.base.po.sys.SysUserToken;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description: spring-boot-base
 * Created by jeikerxiao on 2018/6/28 上午9:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserTokenMapperTest {

    @Autowired
    private SysUserTokenMapper sysUserTokenMapper;

    @Test
    public void queryByToken() {
        String token = "6b1d8a05526035108e18c5101a5fa941";
        SysUserToken userToken = sysUserTokenMapper.queryByToken(token);
        Assert.assertEquals(userToken.getToken(), token);
    }

}