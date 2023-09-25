package com.swkim.myboard.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swkim.myboard.provider.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthencationFilter extends OncePerRequestFilter{

	// request가 들어왔을 때 Request Header의 Authorization 필드의 Bearer Token을 가져옴
	// 가져온 토큰을 검증하고 검증 결과를 SecurityContext에 추가
	
	@Autowired private JwtProvider jwtProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = parseBearerToken(request);

			// token이 null이라면 바로 다음 필터로 넘김
			if (token == null){
				filterChain.doFilter(request, response);
				return ;
			}

			String email = jwtProvider.validate(token);

			if(email == null){
				filterChain.doFilter(request, response);
				return ;
			}

			// SecurityContext에 추가할 객체
			AbstractAuthenticationToken authentication =
					new UsernamePasswordAuthenticationToken(email,  null, AuthorityUtils.NO_AUTHORITIES);
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			// SecurityContextd에 AbstractAuthenticationToken 객체를 추가해서
			// 해당 Thread가 지속적으로 인증 정보를 가질 수 있도록 해줌
			SecurityContext securityContext = SecurityContextHolder.createEmptyContext(); // 빈 컨텍스트
			securityContext.setAuthentication(authentication);
			SecurityContextHolder.setContext(securityContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		filterChain.doFilter(request, response);
	}
	
	// Request Header의 Authorization 필드의 Bearer Token을 가져오는 메서드
	public String parseBearerToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");

		// hasText : null이거나 길이가 0이거나 공백으로만 이루어져 있다면 False를 반환함
		boolean hasAuthorization = StringUtils.hasText(authorization);
		if (!hasAuthorization) return null;

		boolean isBearer = authorization.startsWith("Bearer ");
		if (!isBearer) return null;

		String token = authorization.substring(7);
		return token;
	}

}
