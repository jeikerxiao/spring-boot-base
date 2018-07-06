package com.sinocare.base.core.redis;

/**
 * Description: Redis 统一Key管理
 * Created by jeikerxiao on 2018/7/2 下午1:48
 */
public class RedisKeys {

    public static String getSysCaptchaKey(String key){
        return "sys:captcha:" + key;
    }

}
