package com.dxc.createnewmotopolicy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.dxc.createnewmotopolicy.authentication.MyDBAuthenticationService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	MyDBAuthenticationService myDBAuthenticationService;
	
	
	@Autowired
	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(myDBAuthenticationService);
		
	}
	
	@Override
	protected void configure (HttpSecurity https) throws Exception {
		https.csrf().disable();
		
		//Requirement: Login with User or Admin role
		//If not login, redirect to login page
		https.authorizeRequests().antMatchers("policy_list", "policy_info").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
		
		//Page only for Admin
		https.authorizeRequests().antMatchers("add_policy", "modify_policy").access("hasRole('ROLE_ADMIN')");
		
		//Loged in but not have right role
		https.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		//Config for log in form
		https.authorizeRequests().and().formLogin()
		.loginProcessingUrl("/j_spring_security_check")
		.loginPage("/login")
		.defaultSuccessUrl("/policy_list")
		.failureUrl("/login?error=true")
		.usernameParameter("userName")
		.passwordParameter("password")
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
		
	}
	
	
}