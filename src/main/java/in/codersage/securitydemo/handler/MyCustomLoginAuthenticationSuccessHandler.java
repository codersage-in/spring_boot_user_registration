package in.codersage.securitydemo.handler;

import in.codersage.securitydemo.model.Role;
import in.codersage.securitydemo.model.User;
import in.codersage.securitydemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collection;

@Component("myAuthenticationSuccessHandler")
public class MyCustomLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        Collection<Role> roles = user.getRoles();
        if (roles.size() != 0) {
            redirectStrategy.sendRedirect(request, response, "/home");

        } else {
            request.setAttribute("message", "Role not defined for the user");
            redirectStrategy.sendRedirect(request, response, "/login");
        }
    }
    public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}


