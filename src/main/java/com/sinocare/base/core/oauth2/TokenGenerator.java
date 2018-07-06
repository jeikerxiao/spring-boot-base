package com.sinocare.base.core.oauth2;

import com.sinocare.base.core.exception.ServiceException;
import com.sinocare.base.core.result.ResultCode;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * Description: token 生成器
 * Created by jeikerxiao on 2018/6/27 下午4:46
 */
public class TokenGenerator {

    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    public static String toHexString(byte[] data) {
        if (data == null) {
            return null;
        }
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.TOKEN_GENERATOR_ERROR);
        }
    }
}
