package com.satori.satoriservice.config;

import com.satori.satoribase.config.WebConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @auth YanFuYou
 * @date 03/09/23 上午 12:37
 */
@Configuration
@Import(value = {WebConfig.class})
public class ImportConfig {
}
