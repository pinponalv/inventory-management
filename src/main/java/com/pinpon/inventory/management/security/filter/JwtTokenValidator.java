package com.pinpon.inventory.management.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.pinpon.inventory.management.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class JwtTokenValidator extends OncePerRequestFilter {
    private JwtUtils jwtUtils;

    public JwtTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    //Es un metodo que trae la interfaz OncePerRequestFilter
    //Los parametros siempre son, request, response, filter chain
    //Nunca deben ser nulos - user el import de spring
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws IOException, ServletException {

        //get data of request of login
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(jwtToken != null){
            //le quitamos  bearer y el espacio vacio al token
            jwtToken = jwtToken.substring(7);

            //validate token
            DecodedJWT decodedJWT = jwtUtils.verifyToken(jwtToken);

            //si el token es valido damos acceso
            String email = jwtUtils.extractEmail(decodedJWT);
            String authorities = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

            //lista de authorities, es una cadena de caracteres separadas por comas y lo paso a una authoritiList
            Collection<? extends GrantedAuthority> authoritiesList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

            //get current status of security context holder
            SecurityContext context = SecurityContextHolder.getContext();

            //create new instancia of authentication and add email and permissionsList
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authoritiesList);
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
        }

        chain.doFilter(request, response);
    }
}
