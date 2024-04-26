package com.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@ComponentScan(basePackages = {"com.app"})
@Configuration
@EnableAutoConfiguration(exclude = {DispatcherServletAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class CheckMateApplication {

    public static final String APP_ROOT = "/checkmate";

    public static void main(String[] args) {
        SpringApplication.run(CheckMateApplication.class, args);
    }

    @Bean
    public DispatcherServletRegistrationBean configApi() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ApiConfig.class);
        dispatcherServlet.setApplicationContext(applicationContext);
        DispatcherServletRegistrationBean servletRegistrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, APP_ROOT + "/u/*");
        servletRegistrationBean.setName("Api");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }


}
