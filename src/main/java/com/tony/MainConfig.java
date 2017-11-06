package com.tony;

/**
 * Created by chnho02796 on 2017/11/6.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.system.EmbeddedServerPortFileWriter;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RestController
@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class MainConfig extends WebMvcConfigurerAdapter{
    public static void main(String[] args) {
        SpringApplication.run(MainConfig.class, args);
    }

    /**
     * 自定义异常页
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.FORBIDDEN, "/templates/403.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/templates/404.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/templates/500.html");
            container.addErrorPages(error401Page, error404Page, error500Page);
        });
    }


    @RequestMapping("/security")
    public String security() {
        return "hello world security";
    }

    @RequestMapping("/api")
    public String api() {
        return "hello world api";
    }

    @RequestMapping("/api/admin")
    public String api2() {
        return "hello world api/do";
    }

    @RequestMapping("/api/user")
    public String api3() {
        return "hello world api/as";
    }

}
