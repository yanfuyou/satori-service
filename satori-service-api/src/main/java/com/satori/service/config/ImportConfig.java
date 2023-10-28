package com.satori.service.config;

import com.satori.base.config.JacksonConfig;
import com.satori.base.config.WebConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @auth YanFuYou
 * @date 03/09/23 上午 12:37
 */
@Configuration
@Import(value = {WebConfig.class, JacksonConfig.class})
public class ImportConfig {


    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON,MediaType.MULTIPART_FORM_DATA,MediaType.MULTIPART_MIXED));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }
}
