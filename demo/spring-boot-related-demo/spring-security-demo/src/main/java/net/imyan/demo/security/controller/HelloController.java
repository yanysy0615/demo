package net.imyan.demo.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, path="/hello")
    public String Hello(){
        return "Hello, world!";
    }
}
