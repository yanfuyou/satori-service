package com.satori.satoriservice;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Collections;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
//@RequiredArgsConstructor
public class GeneratorTest {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Test
    public void generatorTable() {
//        System.out.println(System.getProperty("user.dir"));

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.outputDir(System.getProperty("user.dir") + "/src/main/java")
                            .author("yanfuyou")
                            .enableSwagger()
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd hh:mm:ss");
                })
                .packageConfig(builder -> {
                    builder.parent("com.satori.satoriservice")
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .pathInfo(Collections.singletonMap(OutputFile.xml,  System.getProperty("user.dir") + "/src/main/resources/mapper"))
                            .controller("controller");
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user");
                })
                .execute();
    }
}
