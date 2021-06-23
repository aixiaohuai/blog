package com.medusa.blog.common.config;

import com.medusa.blog.common.interceptors.PermissionInterceptor;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getPermissionInterceptor() {
        return new PermissionInterceptor();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxRequestSize(DataSize.ofGigabytes(100));
        factory.setMaxFileSize(DataSize.ofGigabytes(100));
        return factory.createMultipartConfig();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.getPermissionInterceptor());
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String cmd = System.getProperty("user.dir");
        Path path = FileSystems.getDefault().getPath(cmd, "/assets");
        registry.addResourceHandler("assets/**")
                .addResourceLocations("file:" + path.toAbsolutePath().toString() + "/");
    }
}
