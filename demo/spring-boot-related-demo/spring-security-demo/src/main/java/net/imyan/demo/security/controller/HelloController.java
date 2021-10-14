package net.imyan.demo.security.controller;

import net.imyan.demo.security.config.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yanys
 */
@RestController
@RequestMapping("/security_demo")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String hello(Authentication auth, HttpSession httpSession) {
        AtomicReference<String> nickname = new AtomicReference<>("");
        Object principal = auth.getPrincipal();
        if (principal instanceof CustomUserDetails) {
            Optional.ofNullable(((CustomUserDetails) principal).getUserInfo())
                    .ifPresent(userDO -> nickname.set(userDO.getNickname()));
        }
        return String.format("Hello, %s!", nickname.get());
    }
}
