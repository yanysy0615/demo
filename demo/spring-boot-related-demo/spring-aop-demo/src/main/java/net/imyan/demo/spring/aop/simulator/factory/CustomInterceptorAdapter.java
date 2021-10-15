package net.imyan.demo.spring.aop.simulator.factory;

import java.lang.reflect.InvocationTargetException;

/**
 * 抽象的拦截器类适配器
 *
 * @author yanys
 */
public abstract class CustomInterceptorAdapter implements CustomInterceptor{
    @Override
    public boolean before() {
        return true;
    }

    @Override
    public void after() {
    }

    @Override
    public Object around(CustomInvocation customInvocation) throws InvocationTargetException, IllegalAccessException {
        return customInvocation.proceed();
    }

    @Override
    public void afterReturning(Object result) {
    }

    @Override
    public void afterThrowing(Exception e) {
    }
}