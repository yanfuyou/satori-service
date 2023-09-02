package com.satori.satoribase.constant;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * @auth YanFuYou
 * @date 02/09/23 下午 10:08
 */
public class DateFormaters {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern(YYYY_MM_DD);
    public static final DateTimeFormatter DATE_TIME_FORMATER = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);

    public static SimpleDateFormat getDateSimpleFormater(){
        return new SimpleDateFormat(YYYY_MM_DD);
    }

    public static SimpleDateFormat getDateTimeSimpleFormater(){
        return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
    }
}
