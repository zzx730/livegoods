package com.livegoods.login.config;

import com.livegoods.login.service.MyAuthenticationService;
import com.livegoods.login.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private MyAuthenticationService myAuthenticationService;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    // 安全过滤器链
    @Bean
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize ->
                // 对sendyzm放行
                authorize.requestMatchers("/sendyzm").permitAll().anyRequest().authenticated())
                .formLogin()
                // 登录处理url
                .loginProcessingUrl("/login")
                // 登录成功后转发
                .successForwardUrl("/details")
                // 登录成功的处理器
                .successHandler(myAuthenticationService)
                // 登录失败的处理器
                .failureHandler(myAuthenticationService)
                // 记住密码
                .and().rememberMe()
                // token验证保存时间 2周
                .tokenValiditySeconds(1209600)
                // 设置
                .userDetailsService(myUserDetailsService);
            // 关闭crsf防护
            http.csrf().disable();
            // 设置允许跨域
            http.cors().configurationSource(corsConfigurationSource());
        return http.build();
    }

    /**
     * 跨域配置
     * @return
     */
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许跨域的站点
        corsConfiguration.addAllowedOrigin("*");
        // 允许跨域的方法
        corsConfiguration.addAllowedMethod("*");
        // 允许跨域的请求头
        corsConfiguration.addAllowedHeader("*");
        // 设置对所有的url请求生效
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }


}
