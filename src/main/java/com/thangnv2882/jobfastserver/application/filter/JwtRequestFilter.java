package com.thangnv2882.jobfastserver.application.filter;

import com.thangnv2882.jobfastserver.application.service.impl.MyUserDetailsService;
import com.thangnv2882.jobfastserver.application.utils.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private final JwtTokenUtil jwtTokenUtil;

  private final MyUserDetailsService myUserDetailsService;

  public JwtRequestFilter(JwtTokenUtil jwtTokenUtil, MyUserDetailsService myUserDetailsService) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.myUserDetailsService = myUserDetailsService;
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      final String authorizationHeader = request.getHeader("Authorization");

      String username = null;
      String jwt = null;

      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        jwt = authorizationHeader.substring(7);
        username = jwtTokenUtil.extractUsername(jwt);
      }

      if (username != null) {
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.validateToken(jwt, userDetails)) {
          UsernamePasswordAuthenticationToken authenticationToken =
              new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities()
              );
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
      }

    } catch (Exception e) {
      logger.error(e.getMessage());
    }

    filterChain.doFilter(request, response);

  }
}
