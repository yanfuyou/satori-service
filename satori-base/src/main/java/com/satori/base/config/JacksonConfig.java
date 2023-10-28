package com.satori.base.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.satori.base.common.JacksonUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YanFuYou
 * @date 28/10/23 下午 09:07
 */

@Configuration
@ConditionalOnClass(value = {JacksonAutoConfiguration.class, ObjectMapper.class})
public class JacksonConfig {

    @Bean
    public Module satoriModule() {
        return JacksonUtils.satoriModule();
    }
}
