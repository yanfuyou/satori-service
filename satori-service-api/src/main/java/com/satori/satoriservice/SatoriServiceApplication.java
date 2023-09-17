package com.satori.satoriservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.satori.satoriservice.*.mapper")
@SpringBootApplication
public class SatoriServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SatoriServiceApplication.class, args);
    }

}
