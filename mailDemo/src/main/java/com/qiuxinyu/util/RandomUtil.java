package com.qiuxinyu.util;

import com.qiuxinyu.exception.InnerException;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RandomUtil {
    public  static String getRandomCode(int length) {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new InnerException("inner exception");
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = secureRandom.nextInt(10);
            buffer.append(num);
        }
        return new String(buffer);
    }
}
