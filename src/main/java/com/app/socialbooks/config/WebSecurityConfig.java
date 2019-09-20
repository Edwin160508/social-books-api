package com.app.socialbooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	/**
	 * Método responsável por configurar a autenticação da aplicação.
	 * -Nesse exemplo será configurado usuario em memória.
	 * 
	 * @param AuthenticationManagerBuilder
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception {
		auth.inMemoryAuthentication().withUser("algaworks")
		.password("s3nh4").roles("USER");
	}
	
	/**
	 * Método responsável por configurar acesso a fazer requisições na aplicação
	 * 
	 * @param HttpSecurity
	 */
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
			.antMatchers("/h2-console/**").permitAll()
			.anyRequest().authenticated()
		.and()
			.httpBasic()
		.and()
			.csrf().disable();
	}
}
