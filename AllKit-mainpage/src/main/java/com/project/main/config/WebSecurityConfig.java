package com.project.main.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
  
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	//Authentication 로그인 처리
	//Authroization 권한처리
	  @Autowired
	    private DataSource dataSource;

	    @Override  
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .authorizeRequests()
	                    .antMatchers("/", "/account/register", "/**").permitAll()
	                    .anyRequest().authenticated()
	                    .and()
	                .formLogin()
	                    .loginPage("/account/login")
	                    .permitAll()
	                    .and()
	                .logout()
	                    .permitAll();
	    } 

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth)
	            throws Exception {
	        auth.jdbcAuthentication()
	                .dataSource(dataSource)
	                .passwordEncoder(passwordEncoder())
	                .usersByUsernameQuery("select username, password, enabled "
	                        + "from user "
	                        + "where username = ?")
	                .authoritiesByUsernameQuery("select u.username, r.rolename "
	                        + "from userrole ur inner join user u on ur.userid = u.userid "
	                        + "inner join role r on ur.roleid = r.roleid "
	                        + "where u.username = ?");
	    }

	    @Bean
	    public static PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
    
	
	    
	    


           
  
}