package net.imyan.demo.spring.security.config.filter;

import net.imyan.demo.spring.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yansy
 */
@Component
public class WithVerifyCodeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private SecurityConfig securityConfig;

    @PostConstruct
    public void init() {
        try {
            this.setAuthenticationManager(securityConfig.authenticationManager());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String receivedVerifyCode = request.getParameter("verifyCode");
        Object correntVerifyCode = request.getSession().getAttribute("verifyCode");
        if (receivedVerifyCode == null || !receivedVerifyCode.equals(correntVerifyCode)) {
            throw new AuthenticationException("verifyCode error.") {
            };
        }
        return super.attemptAuthentication(request, response);
    }


}
