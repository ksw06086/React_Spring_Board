package com.swkim.myboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.swkim.myboard.filter.JwtAuthencationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired private JwtAuthencationFilter jwtAuthencationFilter;
	
	@Bean
	protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			// cors 정책 (현재는 Application에서 작업을 해줬으므로 기본 설정 사용) 
			.cors().and()
			// csrf 대책 (현재는 CSRF에 대한 대책을 비활성화) - 사이트에 몇 천 유저일 때의 대책
			.csrf().disable()
			// Basic 인증 (현재는 Bearer token 인증방법을 사용하기 때문에 비활성화)
			.httpBasic().disable()
			// 세션 사용 관련 (현재는 Session 기반 인증을 사용하지 않기 때문에 상태를 없앰
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			// '/', '/api/auth' 모듈에 대해서는 모두 허용 (인증을 하지 않고 사용 가능하게 함)
			.authorizeRequests().antMatchers("/", "/api/v1/auth/**", "/api/v1/search/**", "/file/**").permitAll()
			// GET 메서드들은 인증 안해도 허용하겠다.
			.antMatchers(HttpMethod.GET, "/api/v1/board/**", "/api/v1/user/*").permitAll()
			// 나머지 Request에 대해서는 모두 인증된 사용자만 사용가능하게 함
			.anyRequest().authenticated().and()
			// 실패하면 만든 FailedAuthenticationEntryPoint() 반환 값 반환해줌
			.exceptionHandling().authenticationEntryPoint(new FailedAuthenticationEntryPoint());
			
		httpSecurity.addFilterBefore(jwtAuthencationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
	}
	
}

class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) throws IOException, ServletException {
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getWriter().write("{ \"code\": \"AF\", \"message\": \"Authorization Failed\" }");
	}
}
