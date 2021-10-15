package net.imyan.demo.spring.bean.lifecycle.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author yanys
 */
@Component("customTestBean")
public class TestBean implements BeanNameAware, BeanFactoryAware,
        EnvironmentAware, ApplicationContextAware,
        InitializingBean, DisposableBean {

    public TestBean() {
        System.out.println("【TestBean构造函数】");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("【TestBean执行postConstruct】");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("【TestBean执行preDestroy】");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【BeanFactoryAwared接口调用】beanFactory：" + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("【BeanNameAwared接口调用】beanName：" + name);
    }

    @Override
    public void destroy() {
        System.out.println("【DisposableBean接口调用】执行destory()方法");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("【InitializingBean接口调用】执行afterPropertiesSet()方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("【ApplicationContextAware接口调用】applicationContext：" + applicationContext);
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("【EnvironmentAware接口调用】environment：" + environment);
    }
}
