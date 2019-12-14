package com.starmcc.qmframework.tools.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.AntPathMatcher;

/**
 * Spring工具管理器
 *
 * @Author qm
 * @Date 2018年11月24日 上午2:11:03
 */
public class QmSpringManager implements ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(QmSpringManager.class);
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (null == QmSpringManager.applicationContext) {
            QmSpringManager.applicationContext = applicationContext;
            LOG.info("※※※※※※QmSpringManager设置ApplicationContext成功※※※※※※");
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
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz class
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name, 以及Clazz返回指定的Bean
     *
     * @param name  name
     * @param clazz class
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * Spring提供的模糊路径匹配算法
     *
     * @param matchingUrl 匹配路径
     * @param requestUrl  请求地址
     * @return
     */
    public static boolean verifyMatchURI(String matchingUrl, String requestUrl) {
        return new AntPathMatcher().match(matchingUrl, requestUrl);
    }
}
