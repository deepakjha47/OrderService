package com.dpanalytics.OrderService.config;

import com.dpanalytics.OrderService.external.decoder.CustomErrorDecoder;
import com.dpanalytics.OrderService.external.response.ErrorResponse;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * We create to expose the errorDecoder and that will create customErrorDecoder for us
 */
@Configuration
public class FeignConfig {

    @Bean
    ErrorDecoder errorDecoder(){
        return new CustomErrorDecoder();
    }
}
