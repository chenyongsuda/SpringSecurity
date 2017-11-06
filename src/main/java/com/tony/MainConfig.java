package com.tony;

/**
 * Created by chnho02796 on 2017/11/6.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class MainConfig {
    public static void main(String[] args) {
        SpringApplication.run(MainConfig.class, args);
    }

    @RequestMapping("/security")
    public String security() {
        return "hello world security";
    }

    @RequestMapping("/api")
    public String api() {
        return "hello world api";
    }

    @RequestMapping("/api/he")
    public String api2() {
        return "hello world api/do";
    }

    @RequestMapping("/api/hel")
    public String api3() {
        return "hello world api/as";
    }

}
