package com.sinocare.base.service.sys;

import com.sinocare.base.core.common.MessageSourceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

/**
 * Description: 国际化测试
 * Created by jeikerxiao on 2018/7/4 上午9:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysI18nTest {

    @Autowired
    private MessageSourceService messageSourceService;
    @Autowired
    private MessageSource messageSource;

    @Test
    public void testGerman() {
        String message = messageSourceService.getMessage("hello.world");
        Assert.assertNotNull(message);
    }

    @Test
    public void testGermanDe() {
        Locale locale = Locale.GERMAN;
        String message = messageSource.getMessage("hello.world", null, locale);
        Assert.assertEquals(message, "Moin Moin");
    }
}
