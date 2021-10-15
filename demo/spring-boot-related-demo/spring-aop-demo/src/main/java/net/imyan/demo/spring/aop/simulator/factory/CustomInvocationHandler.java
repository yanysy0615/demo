package net.imyan.demo.spring.aop.simulator.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 该类继承了InvocationHandler，重写了invoke方法来实现拦截器逻辑的模板
 * 该类无需对外暴露
 *
 * @author yanys
 */
class CustomInvocationHandler implements InvocationHandler {

    private Object target;
    private CustomInterceptor interceptor;

    CustomInvocationHandler(Object target, CustomInterceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Exception exception = null;
        Object result = null;
        try {
            if (interceptor.before()) {
                CustomInvocation customInvocation = new CustomInvocation(target, args, method);
                result = interceptor.around(customInvocation);
            } else {
                return null;
            }
            interceptor.after();
        } catch (Exception e) {
            exception = e;
        }
        if (exception != null) {
            interceptor.afterThrowing(exception);
            return null;
        } else {
            interceptor.afterReturning(result);
            return result;
        }
    }
}
