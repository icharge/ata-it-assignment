package com.atait.assignment.funds.configuration;

import com.atait.assignment.funds.exception.advise.CustomErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorConfiguration {

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes();
    }

    @Bean
    public CustomErrorController customErrorController(ErrorAttributes errorAttributes) {
        return new CustomErrorController(errorAttributes);
    }
}
