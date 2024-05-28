package ru.kata.spring.boot_security.demo.configs.security;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

    }
}
