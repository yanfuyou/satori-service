package com.satori.base.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

/**
 * @auth YanFuYou
 * @date 03/09/23 上午 12:03
 */
public class SatoriListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {
    private static final Logger log = LoggerFactory.getLogger(SatoriListener.class);
    public static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 4;
    private int order = DEFAULT_ORDER;

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        log.info("执行自定义listener");
//        ConfigurableEnvironment environment = event.getEnvironment();
//        MutablePropertySources mutablePropertySources = environment.getPropertySources();

    }

    @Override
    public int getOrder() {
        return order;
    }
}
