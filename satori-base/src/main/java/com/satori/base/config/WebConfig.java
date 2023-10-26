package com.satori.base.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import com.satori.base.convert.ConsumerConverters;
import com.satori.base.ex.GlobalExceptionHandler;
import com.satori.base.interceptor.BaseIntercepotr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        log.info("扩展messageConverts");
//        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
//        while (iterator.hasNext()) {
//            HttpMessageConverter<?> next = iterator.next();
//            if (next instanceof MappingJackson2HttpMessageConverter) {
//                iterator.remove();
//                break;
//            }
//        }
//        Jackson2ObjectMapperBuilder builder = this.applicationContext.getBean(Jackson2ObjectMapperBuilder.class);
//        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(builder.build());
//
//        converters.add(mappingJackson2HttpMessageConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handler -> {
            SaRouter.match("/**")
                    .notMatch("/api/usr/login")
                    .check(rule -> {

                    });

        })).addPathPatterns("/**");
        registry.addInterceptor(new BaseIntercepotr()).addPathPatterns("/**");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        log.info("允许跨域");
        corsRegistry.addMapping("/**")
                .allowCredentials(false)
                .allowedOrigins(CorsConfiguration.ALL)
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders(CorsConfiguration.ALL)
                .maxAge(3600);
    }
}
