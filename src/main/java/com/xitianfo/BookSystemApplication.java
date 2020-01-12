package com.xitianfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.xitianfo.mapper")
@EnableSwagger2
@SpringBootApplication
public class BookSystemApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BookSystemApplication.class);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BookSystemApplication.class, args);
////        Main main = new Main();
////        main.run();
//        Main main = ctx.getBean(Main.class);
//        main.run();
    }





}
