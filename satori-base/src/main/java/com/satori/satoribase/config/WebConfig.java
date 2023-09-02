package com.satori.satoribase.config;

import com.satori.satoribase.convert.ConsumerConverters;
import com.satori.satoribase.ex.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @auth YanFuYou
 * @date 02/09/23 下午 09:56
 */

@Configuration
@Import(value = {GlobalExceptionHandler.class})
public class WebConfig implements WebMvcConfigurer, ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(WebConfig.class);

    private ApplicationContext applicationContext;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        log.info("添加自定义日期转换器");
        registry.addConverter(ConsumerConverters.String2LocalDateConverter.INSTANCE);
        registry.addConverter(ConsumerConverters.String2LoclaDateTimeConverter.INSTANCE);
        registry.addConverter(ConsumerConverters.String2DateConverter.INSTANCE);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext =  applicationContext;
    }
}
