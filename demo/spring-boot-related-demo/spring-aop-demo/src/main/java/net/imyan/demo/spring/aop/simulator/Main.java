package net.imyan.demo.spring.aop.simulator;

import net.imyan.demo.spring.aop.simulator.factory.CustomInterceptorAdapter;
import net.imyan.demo.spring.aop.simulator.factory.CustomProxyFactory;

/**
 * 主程序
 *
 * @author yanys
 */
public class Main {

    public static void main(String[] args) {
        Foo foo = new FooImpl();
        CustomInterceptorAdapter interceptor = new CustomInterceptorImpl();
        Foo fooProxy = (Foo) CustomProxyFactory.getProxyInstance(foo, interceptor);
        fooProxy.hello();
        System.out.println("===============");
        fooProxy.error();
    }
}
