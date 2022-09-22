package eblog.security;

import java.io.IOException;
import java.nio.charset.MalformedInputException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	//variables
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	private String userName = null;

	private String token = null;
	
	private UserDetails userDetails = null;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestToken = request.getHeader("Authorization");
		
		

		try {
			if (requestToken != null && requestToken.startsWith("Bearer")) {
				token = this.getActualToken(requestToken);
				userName = jwtTokenHelper.extractUsername(token);
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Unable to get ");

		} catch (ExpiredJwtException e) {
			System.out.println("Token has expired");
		} catch (MalformedJwtException e) {
			System.out.println("Invalid Token");
		}

		
		
		if (userName != null && this.isAuthenticatedUserNotExist()) {
			
			 userDetails = this.userDetailsService.loadUserByUsername(userName);
			 
			if (this.isTokenValid(token)) {
				
				this.setAuthenticatedUser(userDetails, request);
				
			}
		}

		filterChain.doFilter(request, response);
	}

	
	
	
	private String getActualToken(String requestToken) {
		return requestToken.substring(7);
	}

	private boolean isAuthenticatedUserNotExist() {
		return SecurityContextHolder.getContext().getAuthentication() == null ? true : false;
	}

	private boolean isTokenValid(String token) {
		return this.jwtTokenHelper.validateToken(token, this.userDetails) == true ? true : false;
	}

	private void setAuthenticatedUser(UserDetails userDetails, HttpServletRequest request) {

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}

}
