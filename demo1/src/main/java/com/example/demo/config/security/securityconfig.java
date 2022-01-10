package com.example.demo.config.security;


import com.example.demo.service.SysUserService;
import entity.SysUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class securityconfig extends WebSecurityConfigurerAdapter {


    private SysUserService userService;

    @Override
    protected UserDetailsService userDetailsService() {
        return username -> {
            SysUser user = userService.findByuserName(username);
            if(null != user){
                return user;
            }throw new UsernameNotFoundException("");
        };
    }

    //配置白名单
    @Override
    public void configure(WebSecurity web) throws Exception {
      web.ignoring()
              .mvcMatchers("/login","/user/info","/card/balance","/card/info");

    }

    //security核心配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //关闭默认session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //请求需要进行认证之后才能访问
        http.authorizeRequests().anyRequest().authenticated();

        //关闭缓存
        http.headers().cacheControl();
        //token过滤器，校验token
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
