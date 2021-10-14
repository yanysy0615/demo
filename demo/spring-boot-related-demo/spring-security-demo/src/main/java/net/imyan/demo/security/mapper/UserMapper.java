package net.imyan.demo.security.mapper;

import net.imyan.demo.security.entity.UserDTO;
import org.springframework.stereotype.Component;

/**
 * @author yanys
 */
@Component
public interface UserMapper {
    UserDTO queryUserDTOByUsername(String username);


}
