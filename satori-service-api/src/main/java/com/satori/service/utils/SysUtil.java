package com.satori.service.utils;

import lombok.experimental.UtilityClass;

/**
 * @author YanFuYou
 * @date 22/10/23 上午 12:03
 */
@UtilityClass
public class SysUtil {

    public static String getAppPath(){
        return System.getProperty("user.dir");
    }
}
