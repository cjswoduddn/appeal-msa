package icu.appeal.gatewayserver.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
/**
 * memberservice로의 요청은 이 필터를 거치치 않는다.
 * 다만, 그 이외 모든 요청은 이 필터를 거쳐 통과해야 처리될 수 있다.
 * jwt토큰 인증을 받아야 한다.
 * 이 필터는 토큰 발급과는 무관하다. 단지 토큰 검사만 할 뿐이다.
 */

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Value("${token.key}")
    private String tokenKey;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(AuthenticationFilter.Config config) {
        return (exchange, chain) -> {
            if(!authentication(exchange.getRequest())) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            return chain.filter(exchange);
        };
    }
    public static class Config{}

    private boolean authentication(ServerHttpRequest request) {
        if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) return false;
        if(!validJwt(
                request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0).replace("Bearer", ""),
                request.getHeaders().get("User-Id").get(0)))
            return false;
        return true;
    }

    private boolean validJwt(String token, String userId) {
        return
                Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(tokenKey.getBytes()))
                        .build().parseClaimsJws(token).getBody().getSubject().equals(userId)
                ;

    }

}
