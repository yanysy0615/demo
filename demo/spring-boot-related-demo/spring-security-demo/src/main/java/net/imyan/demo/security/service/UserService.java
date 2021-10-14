package net.imyan.demo.security.service;

import net.imyan.demo.security.entity.UserDTO;
import net.imyan.demo.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yanys
 */
@Component
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查找用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    public UserDTO queryUserDTOByUsername(String username) {
        return userMapper.queryUserDTOByUsername(username);
    }
}
