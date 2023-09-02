package com.satori.satoribase.convert;

import com.satori.satoribase.constant.DateFormaters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @auth YanFuYou
 * @date 02/09/23 下午 10:04
 */
public class ConsumerConverters {

    private static final Logger log = LoggerFactory.getLogger(ConsumerConverters.class);

    public enum String2LocalDateConverter implements Converter<String, LocalDate> {
        INSTANCE;
        @Override
        public LocalDate convert(String source) {
            return LocalDate.parse(source, DateFormaters.DATE_FORMATER);
        }
    }

    public enum String2LoclaDateTimeConverter implements Converter<String, LocalDateTime>{
        INSTANCE;
        @Override
        public LocalDateTime convert(String source) {
            return LocalDateTime.parse(source,DateFormaters.DATE_TIME_FORMATER);
        }
    }

    public enum String2DateConverter implements Converter<String, Date>{
        INSTANCE;
        @Override
        public Date convert(String source) {
            try {
                if (source.length() <= 10){
                    return DateFormaters.getDateSimpleFormater().parse(source);
                }else {
                    return DateFormaters.getDateTimeSimpleFormater().parse(source);
                }
            }catch (ParseException e){
                log.error("日期转换异常",e);
            }
            return null;
        }
    }

}
