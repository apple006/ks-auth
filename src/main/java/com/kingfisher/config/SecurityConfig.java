package com.kingfisher.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.kingfisher.secruity.AuthenticationFailureHandler;
import com.kingfisher.secruity.AuthenticationSuccessHandler;
import com.kingfisher.secruity.MyFilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Resource(name="myUserDetailService")
	private UserDetailsService myUserDetailService;
	
	@Resource
	private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
//	
//	@Bean
//	public PasswordEncoder createPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    		http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)//在正确的位置添加我们自定义的过滤器  
    		.formLogin().loginPage("/login.html")
    		.loginProcessingUrl("/auth_login").successHandler(authenticationSuccessHandler)
    		.failureHandler(authenticationFailureHandler)
    		.and().authorizeRequests()
    		.antMatchers("/**/*.css", "/img/**", "/**/*.js","/*/api/**","/login.html","/auth_login").permitAll()
    		.anyRequest().authenticated()
    		.and().csrf().disable();
    		
    }
    
    @Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
	
		//启用内存用户存储
		/*auth.inMemoryAuthentication()
		.withUser("user1").password("123456").roles("USER").and()
		.withUser("admin").password("admin").roles("USER","ADMIN");*/
		//
		//给予数据库表认证
		/*auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username,password,enable from t_user where username=?")
		.authoritiesByUsernameQuery("select username,rolename from t_role where username=?");
		*/
		//配置自定义的用户服务
		auth.userDetailsService(myUserDetailService);
		
	}
    
    

}
