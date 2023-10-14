package com.satori.satoribase.common;

import cn.hutool.core.date.format.FastDateFormat;

import java.time.format.DateTimeFormatter;

/**
 * @author YanFuYou
 * @date 15/10/23 上午 02:26
 */
public class Formaters {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(YYYY_MM_DD);
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final DateTimeFormatter YYYYMMDDHHMMSS_FORMATTER = DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS);
    public static final FastDateFormat FAST_DATE_FORMAT = FastDateFormat.getInstance(YYYY_MM_DD);
    public static final FastDateFormat FAST_DATE_TIME_FORMAT = FastDateFormat.getInstance(YYYY_MM_DD_HH_MM_SS);
}
