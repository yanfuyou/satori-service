package com.satori.service.utils;

import cn.hutool.core.util.StrUtil;
import com.satori.model.enums.SystemCodeEnum;
import com.satori.model.ex.BaseException;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @auth YanFuYou
 * @date 10/09/23 上午 03:04
 */
public class PasswordUtil {


    /**
     * 密文生成
     * @param pwd 密码
     * @param salt 盐值
     * @return 加密结果
     */
    public static Map<String,String> encodePassword(String pwd,String salt) {
        MessageDigest sha256;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        }catch (Exception e){
            throw new BaseException(SystemCodeEnum.SYS_INTERNAL_ERR.getCode(),SystemCodeEnum.SYS_INTERNAL_ERR.getDesc());
        }
        HashMap<String, String> map = new HashMap<>();
        if (StrUtil.isBlank(salt)){
            salt = getSalt();
        }
        String mixedPwd = mixedPwd(pwd, salt);
        byte[] pwdBytes = mixedPwd.getBytes();
        byte[] hash = sha256.digest(pwdBytes);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hash) {
            String hex = String.format("%02x", b);
            stringBuilder.append(hex);
        }
        map.put("ciphertext",stringBuilder.toString());
        map.put("salt",salt);
        return map;
    }

    /**
     * 密码校验
     * @param sourcePwd 用户输入的
     * @param targetPwd 数据库的
     * @param salt 密码盐值
     * @return 校验结果
     */
    public static boolean checkPassword(String sourcePwd,String targetPwd,String salt) {
        Map<String, String> map = new HashMap<>();
        try {
             map = encodePassword(sourcePwd, salt);
        }catch (Exception e){
            throw new BaseException(SystemCodeEnum.SYS_INTERNAL_ERR.getCode(),SystemCodeEnum.SYS_INTERNAL_ERR.getDesc());
        }
        String ciphertext = map.get("ciphertext");
        return ciphertext.equals(targetPwd);
    }


    /**
     * 随机生成盐值
     * @return
     */
    private static String getSalt(){

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            captcha.append(randomChar);
        }
        return captcha.toString();
    }

    /**
     * 混合密码
     * @param pwd 明文
     * @param salt 盐值
     * @return 密文
     */
    private static String mixedPwd(String pwd,String salt){
        return salt + pwd + "yanfuyou" + pwd;
    }
}
