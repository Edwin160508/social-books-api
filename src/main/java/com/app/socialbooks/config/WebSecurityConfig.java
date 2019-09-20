package com.app.socialbooks.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Método responsável por configurar a autenticação da aplicação. -Nesse exemplo
	 * será configurado usuario em memória.
	 * 
	 * @param AuthenticationManagerBuilder
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("admin")).roles("USER");
	}

	/**
	 * Método responsável por configurar acesso a fazer requisições na aplicação
	 * 
	 * @param HttpSecurity
	 */
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginProcessingUrl("/login").permitAll().and().authorizeRequests()
				.antMatchers("/h2-console/**").permitAll().anyRequest().authenticated().and().httpBasic().and().csrf()
				.disable();
	}

}