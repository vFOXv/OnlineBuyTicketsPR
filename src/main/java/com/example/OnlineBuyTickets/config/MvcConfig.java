package com.example.OnlineBuyTickets.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    public void addViewControllers(ViewControllerRegistry registry){
        //addViewController URL        setViewName путь до HTML файла
        registry.addViewController("/").setViewName("Start/start");
        registry.addViewController("login").setViewName("Security/login");
        //registry.addViewController("menu").setViewName("Start/menu");
    }
}
