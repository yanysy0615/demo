package net.imyan.demo.spring.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yanys
 */
@SpringBootApplication
@MapperScan(basePackages = "net.imyan.demo.spring.security.mapper")
public class SpringSecurityDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class);
    }

}
