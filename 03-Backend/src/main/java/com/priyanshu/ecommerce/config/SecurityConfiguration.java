package com.priyanshu.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.okta.spring.boot.oauth.Okta;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//protect endpoint /api/orders
		http.authorizeRequests()
				.antMatchers("/api/orders/**")
				.authenticated()
				.and()
				.oauth2ResourceServer()
				.jwt();
		
		//add support for cors filter
		http.cors();
		
		//force a non-empty response body for 401's to make the respone more user friendly
		Okta.configureResourceServer401ResponseBody(http);
		
		//disable CSRF since we are not using Cookies for session traking
		http.csrf().disable();
		
		
	}
	
}
