package net.imyan.demo.spring.security.mapper;

import net.imyan.demo.spring.security.entity.UserDTO;
import org.springframework.stereotype.Component;

/**
 * @author yanys
 */
@Component
public interface UserMapper {
    UserDTO queryUserDTOByUsername(String username);


}
