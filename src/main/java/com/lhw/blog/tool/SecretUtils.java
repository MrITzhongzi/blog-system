package com.lhw.blog.tool;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description:
 * @author: lihongwei
 * @time: 2020/4/8 4:59 下午
 */
public class SecretUtils {

    /**
     * 字符串加密
     * @param oldPwd
     * @return
     */
    public static String generatePwd(String oldPwd) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            // 生成32位字符串秘文
            sha.update(oldPwd.getBytes());
            return new BigInteger(sha.digest()).toString(32);
        } catch (Exception e) {

        }
        return null;
    }
}
