package net.imyan.demo.spring.bean.scope.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component("requestBean")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RequestBean {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
