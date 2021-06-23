package com.medusa.blog.common.config;

import com.medusa.blog.model.SpecColumn;
import com.medusa.blog.service.SpecColumnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;


@Component
@Slf4j
public class ApplicationStartup implements ApplicationRunner, ServletContextAware {

    ServletContext servletContext;

    @Autowired
    private SpecColumnService specColumnService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("-------------->初始化加载数据start...");

        List<SpecColumn> specColumns = specColumnService.queryColumnList();
        servletContext.setAttribute("specColumns",specColumns);
    }


    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
