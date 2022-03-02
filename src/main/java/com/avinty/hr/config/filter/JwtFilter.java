package com.avinty.hr.config.filter;

import com.avinty.hr.config.util.JwtUtil;
import com.avinty.hr.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

  @Autowired private JwtUtil jwtUtil;
  @Autowired private EmployeesService service;

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {

    String authorizationHeader = httpServletRequest.getHeader("Authorization");

    String token = null;
    String userName = null;

    if (authorizationHeader != null) {
      token = authorizationHeader;
      userName = jwtUtil.extractUsername(token);
    }

    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      User userDetails = service.loadUserByUsername(userName);

      if (jwtUtil.validateToken(token, userDetails)) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
