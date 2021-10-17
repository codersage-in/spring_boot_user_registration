package in.codersage.securitydemo.handler;

import in.codersage.securitydemo.model.User;
import in.codersage.securitydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        final User user = userRepository. findUserByUsername
                (auth.getName());
        if ((user == null)) {
            throw new BadCredentialsException("User not found");
        }
        Authentication result = super.authenticate(auth);
        UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(
                user, result.getCredentials(), result.getAuthorities());
        return authResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

