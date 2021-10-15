package net.imyan.demo.spring.aop.simulator.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 该类封装了当前被拦截方法的相关信息
 * 该类无需对外暴露构造方法
 * @author yanys
 */
public class CustomInvocation {
    private Object target;
    private Object[] args;
    private Method method;

    CustomInvocation(Object target, Object[] args, Method method) {
        this.target = target;
        this.args = args;
        this.method = method;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }
}
