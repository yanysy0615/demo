package net.imyan.demo.spring.bean.lifecycle.config;

import net.imyan.demo.spring.bean.lifecycle.entity.TestBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author yanys
 */
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TestBean) {
            System.out.println("【BeanPostProcessor前置处理】BeanName：" + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TestBean) {
            System.out.println("【BeanPostProcessor后置处理】BeanName：" + beanName);
        }
        return bean;
    }
}
