package net.imyan.demo.security.config;

import net.imyan.demo.security.config.filter.NoPasswordFilter;
import net.imyan.demo.security.config.filter.WithVerifyCodeAuthenticationFilter;
import net.imyan.demo.security.entity.UserDTO;
import net.imyan.demo.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


/**
 * @author yanys
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private NoPasswordFilter noPasswordFilter;

    @Autowired
    private WithVerifyCodeAuthenticationFilter withVerifyCodeAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/security_demo/verify_code").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/**/*.ico", "/**/*.css", "/**/*.js", "/**/*.jpg").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .and().csrf().disable().cors().disable();

///       登录验证过滤器的切换
//        http.addFilterBefore(noPasswordFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(withVerifyCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Component
    class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private UserService userService;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserDTO userDTO = userService.queryUserDTOByUsername(username);
            if (userDTO == null) {
                throw new UsernameNotFoundException(username);
            }
            UserDetails user = User.withUsername(username)
                    .password(userDTO.getPassword())
                    .roles(userDTO.getRoles().toArray(new String[0]))
                    .authorities(userDTO.getAuthorities().toArray(new String[0]))
                    .build();
            return new CustomUserDetails(userDTO, user);
        }
    }
}
