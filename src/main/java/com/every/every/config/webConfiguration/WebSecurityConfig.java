//package com.every.every.config.webConfiguration;
//
//import com.every.every.config.customHandlers.CustomAuthenticationEntryPoint;
//import com.every.every.config.customHandlers.CustomAuthenticationFailureHandler;
//import com.every.every.config.customHandlers.CustomLogoutSuccessHandler;
//import com.every.every.config.customHandlers.CustomSimpleUrlAuthenticationSuccessHandler;
//import com.every.every.service.entityService.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserService userService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(8);
//    }
//
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        return new CustomAuthenticationEntryPoint();
//    }
//
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new CustomAuthenticationFailureHandler();
//    }
//
//    @Bean
//    public LogoutSuccessHandler logoutSuccessHandler() {
//        return new CustomLogoutSuccessHandler();
//    }
//
//    @Bean
//    public SimpleUrlAuthenticationSuccessHandler simpleUrlAuthenticationSuccessHandler() {
//        return new CustomSimpleUrlAuthenticationSuccessHandler();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
////        http.csrf().disable();
//
//        http
//                .authorizeRequests()
//                .antMatchers("/auth/**").permitAll();
//        http
//                .authorizeRequests()
//                .antMatchers("/login*").anonymous();
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/registration", "/alive").permitAll()
////                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginProcessingUrl("/auth/login")
////                .loginPage("/login")
//                .permitAll()
//                .and()
////                .rememberMe()
////                .and()
//                .logout()
//                .permitAll();
//
////        http.authorizeRequests().antMatchers("*").permitAll(); //Строка для тестирования
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//
//}
