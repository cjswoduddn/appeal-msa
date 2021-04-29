package icu.appeal.memberserver.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.appeal.commonlibrary.dto.member.SignInDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String USERNAME = "email";
    public static final String PASSWORD = "password";
    public static final String URL = "/signin";
    private boolean postOnly = true;
    private String key;

    public void setKey(String key){
        this.key = key;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        try {
            SignInDto signInDto = new ObjectMapper().readValue(request.getInputStream(), SignInDto.class);
            String username = signInDto.getEmail();
            username = (username != null) ? username : "";
            username = username.trim(); // 맨앞 맨뒤 공백 전부 제거
            String password = signInDto.getPassword();
            password = (password != null) ? password : "";
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, token);
            return this.getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException();       // 어떻게 처리할까?
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((User)authResult.getPrincipal()).getUsername();
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+1000))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact()
                ;
        response.addHeader("token", token);
        response.addHeader("User-Id", username);
    }
}
