package net.imyan.demo.spring.aop.simulator.factory;

import java.lang.reflect.InvocationTargetException;

/**
 * 拦截器类接口，规定了拦截点
 *
 * @author yanys
 */
public interface CustomInterceptor {
    /**
     * 方法执行前调用
     *
     * @return true则往下继续方法执行，false则直接返回null
     */
    boolean before();

    /**
     * 方法执行后调用
     */
    void after();

    /**
     * 环绕型代理
     *
     * @param customInvocation 封装当前方法执行信息的对象
     * @return 方法的执行结果
     */
    Object around(CustomInvocation customInvocation) throws InvocationTargetException, IllegalAccessException;

    /**
     * 正确返回时调用
     *
     * @param result 方法调用准备返回的结果
     */
    void afterReturning(Object result);

    /**
     * 方法抛出异常时调用
     *
     * @param e 方法抛出的异常
     */
     void afterThrowing(Exception e);
}