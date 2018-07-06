package com.sinocare.base.service.sys;

import com.sinocare.base.core.redis.RedisKeys;
import com.sinocare.base.core.redis.RedisUtils;
import com.sinocare.base.po.sys.SysCaptcha;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Description: Redis 测试
 * Created by jeikerxiao on 2018/7/2 下午1:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRedisTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void redisSetAndGet() {
        String uuid = "1234";
        SysCaptcha sysCaptcha1 = new SysCaptcha();
        sysCaptcha1.setCode("asdfq");
        sysCaptcha1.setUuid(uuid);
        sysCaptcha1.setExpireTime(new Date());
        // 设置值
        redisUtils.set(RedisKeys.getSysCaptchaKey(uuid), sysCaptcha1);
        // 取值验证
        SysCaptcha sysCaptcha2 = redisUtils.get(RedisKeys.getSysCaptchaKey(uuid), SysCaptcha.class);
        Assert.assertEquals(sysCaptcha1.getCode(), sysCaptcha2.getCode());
    }

    @Test
    public void redisDelete() {
        String uuid = "1234";
        // 删除
        redisUtils.delete(RedisKeys.getSysCaptchaKey(uuid));
        // 验证删除
        SysCaptcha sysCaptcha = redisUtils.get(RedisKeys.getSysCaptchaKey(uuid), SysCaptcha.class);
        Assert.assertNull(sysCaptcha);
    }

}
