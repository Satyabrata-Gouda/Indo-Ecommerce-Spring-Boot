package com.ecommerce.indo.filter;


import ch.qos.logback.core.util.StringUtil;
import com.ecommerce.indo.constant.Jwt;
import com.ecommerce.indo.exception.AuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
          String jwt =  request.getHeader(Jwt.JWT_HEADER);
          if(StringUtils.isNotBlank(jwt)){
              jwt = jwt.substring(7);
              SecretKey key = Keys.hmacShaKeyFor(Jwt.SECRET_KEY.getBytes());
              Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJwt(jwt).getBody();

              String email = String.valueOf(claims.get("email"));

              String authorities =  String.valueOf(claims.get("authorities"));

              List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

              Authentication authentication =  new UsernamePasswordAuthenticationToken(email,null,auths);

              SecurityContextHolder.getContext().setAuthentication(authentication);
          }
        }catch (Exception e){
            throw new AuthException("Invalid Token Found");

        }
        filterChain.doFilter(request,response);

    }


}
