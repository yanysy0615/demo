package net.imyan.demo.spring.security.config;

import net.imyan.demo.spring.security.entity.UserDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author yanys
 */
public class CustomUserDetails extends User {
    private UserDTO userInfo;

    public CustomUserDetails(UserDTO userDTO, UserDetails user) {
        super(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
                user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getAuthorities());
        this.setUserInfo(userDTO);
    }

    public UserDTO getUserInfo() {
        return userInfo;
    }

    private void setUserInfo(UserDTO userInfo) {
        this.userInfo = userInfo;
    }
}
