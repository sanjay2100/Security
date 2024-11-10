package com.example.security.security.Config.SecurityFilter;

import com.example.security.security.Dto.ValidTokenDto;
import com.example.security.security.Modals.User;
import com.example.security.security.Repository.UserRepository;
import com.example.security.security.Service.JwtService;
import com.example.security.security.Service.RedisService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RedisService redisService;

    @Autowired
    ApplicationContext context;
    @Autowired
    private UserRepository repository;

    public String getToken(HttpServletRequest request) {
        String headers = request.getHeader("Authorization");
        if (headers != null && headers.startsWith("Bearer ")) {
            return headers.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if (token != null) {
            try {
                ValidTokenDto isValid = jwtService.ValidateToken(token);
                if (isValid.isValid()) {
                    String userName = redisService.getUserNameFromToken(token);

                    UserDetails user = context.getBean(UserDetailsService.class).loadUserByUsername(userName);
                    System.out.print(user);
                    if (user != null) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}
