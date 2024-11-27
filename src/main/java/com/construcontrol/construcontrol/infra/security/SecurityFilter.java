package com.construcontrol.construcontrol.infra.security;

import com.construcontrol.construcontrol.repositories.users.UserRepository;
import com.construcontrol.construcontrol.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  TokenService tokenService;
  @Autowired
  UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    var token = this.recoverToken(request);
    if (token != null) {
      var login = tokenService.validateToken(token);
      if (login != null) {
        UserDetails userDetails = userRepository.findByEmail(login);
        if (userDetails != null) {
          var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
          logger.error("UserDetails is null for login: " + login);
        }
      } else {
        logger.error("Login is null, invalid token");
      }
    }
    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if (authHeader == null) return null;
    return authHeader.replace("Bearer ", "");
  }
}
