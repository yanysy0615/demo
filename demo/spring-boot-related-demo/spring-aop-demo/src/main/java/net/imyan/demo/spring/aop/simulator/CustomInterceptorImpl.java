package net.imyan.demo.spring.aop.simulator;

import net.imyan.demo.spring.aop.simulator.factory.CustomInterceptorAdapter;
import net.imyan.demo.spring.aop.simulator.factory.CustomInvocation;

import java.lang.reflect.InvocationTargetException;

/**
 * 自定义的拦截器类，在拦截点处实现了具体的拦截方法
 *
 * @author yanys
 */
public class CustomInterceptorImpl extends CustomInterceptorAdapter {
    @Override
    public boolean before() {
        System.out.println("【before()方法调用】");
        return true;
    }

    @Override
    public void after() {
        System.out.println("【after()方法调用】");
    }

    @Override
    public Object around(CustomInvocation customInvocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("【around()方法前置调用】");
        Object result = customInvocation.proceed();
        System.out.println("【around()方法后置调用】");
        return result;
    }

    @Override
    public void afterReturning(Object result) {
        System.out.println("【afterReturning()方法调用】");
    }

    @Override
    public void afterThrowing(Exception e) {
        System.out.println("【afterThrowing()方法调用】");
    }
}
