package com.ecom.configuration;

import com.ecom.entity.AppUser;
import com.ecom.reposiroty.AppUserRepository;
import com.ecom.service.ServiceImpl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;
import java.util.Optional;

@Configuration
public class JWTFiler extends OncePerRequestFilter {


    private JwtService jwtService;
    private AppUserRepository appUserRepository;


    public JWTFiler(JwtService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken=request.getHeader("Authorization");
        if(bearerToken!=null && bearerToken.startsWith("Bearer ")){
            String token = bearerToken.substring(8, bearerToken.length() - 1);
            String name = jwtService.getClaim(token);
            Optional<AppUser> byName = appUserRepository.findByName(name);
            if(byName.isPresent()){
                AppUser appUser = byName.get();
                UsernamePasswordAuthenticationToken auth
                        =new UsernamePasswordAuthenticationToken(appUser,null,null);
                auth.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);

            }

        }

        filterChain.doFilter(request,response);
    }


}
