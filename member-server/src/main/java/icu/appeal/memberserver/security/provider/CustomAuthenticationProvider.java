package icu.appeal.memberserver.security.provider;

import icu.appeal.memberserver.security.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailService userDetailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = (User)userDetailService.loadUserByUsername(authentication.getName());
        checkPassword(authentication.getCredentials(), user.getPassword());
        // checkRole will be updated!
        return new UsernamePasswordAuthenticationToken(user, null);
    }

    private void checkPassword(Object principal, String password) {
        if(!passwordEncoder.matches((String)principal, password)){
            throw new BadCredentialsException("password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
