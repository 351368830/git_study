package com.zl;

import com.zl.common.MarkerInterface;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = {"com.zl.mapper"},markerInterface = MarkerInterface.class)
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
