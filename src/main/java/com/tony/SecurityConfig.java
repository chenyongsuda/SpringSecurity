package com.tony;

/**
 * Created by chnho02796 on 2017/11/6.
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**定义认证用户信息获取来源，密码校验规则等*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //inMemoryAuthentication 从内存中获取
        auth.inMemoryAuthentication().withUser("chengli").password("123456").roles("USER");

        //jdbcAuthentication从数据库中获取，但是默认是以security提供的表结构
        //usersByUsernameQuery 指定查询用户SQL
        //authoritiesByUsernameQuery 指定查询权限SQL
        //auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query).authoritiesByUsernameQuery(query);

        //注入userDetailsService，需要实现userDetailsService接口
        //auth.userDetailsService(userDetailsService);
    }

    /**定义安全策略*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
            http.authorizeRequests()//配置安全策略
                    .antMatchers("/open/**").permitAll()//定义/请求不需要验证
                    .antMatchers("/api/admin").hasRole("ADMIN")
                    .antMatchers("/api/user").hasRole("USER")
                    .anyRequest().authenticated()//其余的所有请求都需要验证
                    .and()
                    .logout()
                    .permitAll()//定义logout不需要验证
                    .and()
                    .formLogin()
                    .loginPage("/login")
//                    .successForwardUrl("/successLogin")
//                    .failureForwardUrl("/errorLogin")
                    .permitAll();//使用form表单登录
    }

}
