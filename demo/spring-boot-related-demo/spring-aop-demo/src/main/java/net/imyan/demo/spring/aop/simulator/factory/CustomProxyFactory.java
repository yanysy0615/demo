package net.imyan.demo.spring.aop.simulator.factory;

import net.imyan.demo.spring.aop.simulator.CustomInterceptorImpl;
import net.imyan.demo.spring.aop.simulator.Foo;
import net.imyan.demo.spring.aop.simulator.FooImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author yanys
 */
public class CustomProxyFactory {

    /**
     * 用生成代理对象
     *
     * @param target      被代理对象
     * @param interceptor 拦截器
     * @return 代理对象
     */
    public static Object getProxyInstance(Object target, CustomInterceptor interceptor) {
        InvocationHandler invocationHandler = new CustomInvocationHandler(target, interceptor);
        return Proxy.newProxyInstance(CustomProxyFactory.class.getClassLoader(), target.getClass().getInterfaces(), invocationHandler);
    }

    /**
     * 主程序执行入口
     *
     * @param args 主程序入口参数
     */
    public static void main(String[] args) {
        Foo foo = new FooImpl();
        CustomInterceptorAdapter interceptor = new CustomInterceptorImpl();
        Foo fooProxy = (Foo) CustomProxyFactory.getProxyInstance(foo, interceptor);
        fooProxy.hello();
        System.out.println("===============");
        fooProxy.error();
    }

}
