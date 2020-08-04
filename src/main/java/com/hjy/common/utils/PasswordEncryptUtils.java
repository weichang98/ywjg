package com.hjy.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordEncryptUtils {
    public static String MyPasswordEncryptUtil(String username, String password){
        //加密方式
        String algorithmName = "MD5";
        //盐
        Object salt = ByteSource.Util.bytes(username);
        //hash次数
        int hashIterations = 1;
        SimpleHash simpleHash = new SimpleHash(algorithmName, password, salt, hashIterations);
        return simpleHash.toString();
    }
}
