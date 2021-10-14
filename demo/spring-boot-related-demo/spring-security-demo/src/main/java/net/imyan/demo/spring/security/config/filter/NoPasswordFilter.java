package net.imyan.demo.spring.security.config.filter;

import net.imyan.demo.spring.security.config.CustomUserDetails;
import net.imyan.demo.spring.security.config.SecurityConfig;
import net.imyan.demo.spring.security.entity.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yanys
 */

// 注：@Component注册方式可以解决循环依赖，@Bean注册方式不可以
@Component()
public class NoPasswordFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private SecurityConfig securityConfig;

    // 注： @PostConstruct注解的方法不能有任何CheckedException抛出
    @PostConstruct
    public void init() {
        try {
            this.setAuthenticationManager(securityConfig.authenticationManager());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserDTO userDTO = new UserDTO();

        // 验证用户名
        String username = obtainUsername(request);
        if (username == null) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        username = username.trim();
        userDTO.setUsername(username);
        userDTO.setNickname(username);

        // 解析用户角色（默认角色为VISITOR）
        String roles = request.getParameter("roles");
        List<String> roleList = new ArrayList<>();
        if (roles != null) {
            roleList = Arrays.asList(roles.split(","));
        }
        userDTO.setRoles(roleList);

        // 解析用户权限
        String authorities = request.getParameter("authorities");
        List<String> authorityStrList = new ArrayList<>();
        if (authorities != null) {
            authorityStrList = Arrays.asList(authorities.split(","));
        }
        userDTO.setAuthorities(authorityStrList);

        // 生成用户token
        UserDetails user = User.withUsername(username).password("")
                .authorities(authorityStrList.toArray(new String[0])).build();
        CustomUserDetails userDetails = new CustomUserDetails(userDTO, user);

        List<GrantedAuthority> authorityList = Arrays.asList(authorityStrList.stream()
                .map(SimpleGrantedAuthority::new).toArray(GrantedAuthority[]::new));
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorityList);
        setDetails(request, authToken);
        return authToken;
    }
}
