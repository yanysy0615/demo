package net.imyan.demo.spring.aop.simulator;

/**
 * 测试用的被封装类的接口
 *
 * @author yanys
 */
public interface Foo {
    /**
     * 输出欢迎信息，用于测试。
     */
    void hello();

    /**
     * 抛出异常，用于测试。
     */
    void error();
}
