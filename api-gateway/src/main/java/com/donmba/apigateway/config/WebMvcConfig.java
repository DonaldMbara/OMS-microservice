package com.donmba.apigateway.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.donmba.apigateway.config.interceptor.BearerTokenInterceptor;
import com.donmba.apigateway.config.interceptor.BearerTokenWrapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:4200")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(-1);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // register the interceptor
        registry.addInterceptor(bearerTokenInterceptor())
                .excludePathPatterns("/actuator");
    }

    // the 2 methods below produces the bean for token wrapper and interceptor in
    // request scope

    @Bean
    public BearerTokenInterceptor bearerTokenInterceptor() {
        return new BearerTokenInterceptor(bearerTokenWrapper());
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public BearerTokenWrapper bearerTokenWrapper() {
        return new BearerTokenWrapper();
    }
}
