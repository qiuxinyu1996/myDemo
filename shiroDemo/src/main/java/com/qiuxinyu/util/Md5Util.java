package com.qiuxinyu.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Md5Util {
    private static final String SALT = "salt";

    public static String encrypt(String password) {
        return new SimpleHash("md5", password, SALT, 1024).toHex();
    }
}
