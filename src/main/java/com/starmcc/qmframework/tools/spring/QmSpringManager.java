package com.starmcc.qmframework.tools.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.AntPathMatcher;

/**
 * @author starmcc
 * @version 2018年11月24日 上午2:11:03
 * Spring工具管理器
 */
public class QmSpringManager implements ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(QmSpringManager.class);
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (null == QmSpringManager.applicationContext) {
            QmSpringManager.applicationContext = applicationContext;
            LOG.info("※ QmSpringManager add applicationContext ※");
        }
    }

    /**
     * 获取applicationContext
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name name
     * @return Returns the specified data according to the method
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz class
     * @return Returns the specified data according to the method
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name, 以及Clazz返回指定的Bean
     *
     * @param name  name
     * @param clazz class
     * @return Returns the specified data according to the method
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * Spring提供的模糊路径匹配算法
     *
     * @param matchingUrl 匹配路径
     * @param requestUrl  请求地址
     * @return Returns the specified data according to the method
     */
    public static boolean verifyMatchURI(String matchingUrl, String requestUrl) {
        return new AntPathMatcher().match(matchingUrl, requestUrl);
    }

    /**
     * 动态注入bean
     *
     * @param requiredType 注入类
     * @param beanName     bean名称
     */
    public static void registerBean(Class<?> requiredType, String beanName) {
        //将applicationContext转换为ConfigurableApplicationContext
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        //获取BeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getAutowireCapableBeanFactory();
        //创建bean信息.
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(requiredType);
        //动态注册bean.
        defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }
}
