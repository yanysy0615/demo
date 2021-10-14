package net.imyan.demo.security.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.Iterator;

/**
 * @author yanys
 */
@Component
public class SecurityFilterChainBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (FilterChainProxy.class.isAssignableFrom(bean.getClass())) {
            FilterChainProxy fcp = (FilterChainProxy) bean;
            Iterator<Filter> iterator = fcp.getFilterChains().get(0).getFilters().iterator();
            while (iterator.hasNext()) {
                Filter filter = iterator.next();
                if (filter.getClass() == UsernamePasswordAuthenticationFilter.class) {
                    iterator.remove();
                    break;
                }
            }
        }
        return bean;
    }
}
