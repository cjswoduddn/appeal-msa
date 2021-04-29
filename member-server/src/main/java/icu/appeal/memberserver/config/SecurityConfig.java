package icu.appeal.memberserver.config;

import icu.appeal.memberserver.security.filter.JsonUsernamePasswordAuthenticationFilter;
import icu.appeal.memberserver.security.provider.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationProvider authenticationProvider;

    @Value("${token.key}")
    private String key;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    private JsonUsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        JsonUsernamePasswordAuthenticationFilter authenticationFilter = new JsonUsernamePasswordAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(this.authenticationManagerBean());
        authenticationFilter.setFilterProcessesUrl(JsonUsernamePasswordAuthenticationFilter.URL);
        authenticationFilter.setUsernameParameter(JsonUsernamePasswordAuthenticationFilter.USERNAME);
        authenticationFilter.setPasswordParameter(JsonUsernamePasswordAuthenticationFilter.PASSWORD);
        authenticationFilter.setKey(key);
        return authenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().hasIpAddress("127.0.0.1")
                .and()
                .csrf().disable()
                .formLogin().disable()
                .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        ;
    }
}
