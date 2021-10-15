package net.imyan.demo.spring.aop.simulator;

/**
 * 测试用的被封装类
 *
 * @author yanys
 */
public class FooImpl implements Foo {
    @Override
    public void hello() {
        System.out.println("Hello, world!");
    }

    @Override
    public void error() {
        System.out.println("prepare to throw Exception.");
        throw new RuntimeException("测试用的异常");
    }
}