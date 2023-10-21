package com.satori.service.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @auth YanFuYou
 * @date 17/09/23 下午 07:36
 */
@Component
@Lazy(value = false)
public class WebsocketApplicationContextAware implements ApplicationContextAware {

    private static ApplicationContext APPLICATION_CONTEXT;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT=applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return APPLICATION_CONTEXT;
    }
}
