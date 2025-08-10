package com.wez.crm.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtSecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);

            /*if (RouteUtil.isPublicResource(request.getRequestURI())) {
                filterChain.doFilter(request, response);
                return;
            }

            String accessToken = JwtUtil.extractToken(request);

            if (StringUtil.isNullOrEmpty(accessToken) || !JwtUtil.isValidToken(accessToken)) {
                String errorMessage = StringUtil.isNullOrEmpty(accessToken) ? "Extracted access token is empty." : "Invalid access token.";
                throw new BadCredentialsException(errorMessage);
            }

            CustomUserDetailsImpl userDetails = JwtUtil.extractUserDetails(accessToken);

            if (!isValidUser(userDetails.getUserId()))
                throw new BadCredentialsException("Invalid user id provided in the access token. UserId: " + userDetails.getUserId());

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);*/
        } catch (Exception ex) {
            throw new BadCredentialsException("Authentication failed due to internal server error. Error: " + ex.getMessage());
        }
    }

    private boolean isValidUser(Long userId) {
        //Fetch User and check if exists
        return true;
    }
}
