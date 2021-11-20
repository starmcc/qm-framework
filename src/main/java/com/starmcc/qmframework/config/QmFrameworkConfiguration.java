package com.starmcc.qmframework.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.starmcc.qmframework.body.JsonPathArgumentResolver;
import com.starmcc.qmframework.filter.InitFilter;
import com.starmcc.qmframework.tools.spring.QmSpringManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author starmcc
 * @version 2018年11月24日 上午1:33:11
 * QmFramework 核心组合配置类
 */
public class QmFrameworkConfiguration implements WebMvcConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(QmFrameworkConfiguration.class);

    private static final String GIT_HUB_URL = "https://github.com/starmcc/qm-framework";


    /**
     * 项目启动时，只执行一次
     */
    @PostConstruct
    private final void init() {
    }

    /**
     * 项目停止执行
     */
    @PreDestroy
    private final void preDestroy() {
        LOG.info("※ Service stopped ※");
        LOG.info("浅梦GitHub {}", GIT_HUB_URL);
    }

    /**
     * 初始化QmSpringManager
     *
     * @return Returns the specified data according to the method
     */
    @Bean
    public QmSpringManager initQmSpringManager() {
        return new QmSpringManager();
    }

    /**
     * 初始化过滤器
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<InitFilter> initFilter() {
        FilterRegistrationBean<InitFilter> registration = new FilterRegistrationBean<>();
        //注入过滤器
        registration.setFilter(new InitFilter());
        //拦截规则
        registration.addUrlPatterns("/*");
        //过滤器名称
        registration.setName("initFilter");
        //过滤器顺序
        registration.setOrder(1);
        return registration;
    }

    /**
     * 配置消息转换器--alibaba fastjson
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        // 2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteDateUseDateFormat);
        // 3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        // 4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        // 5.将convert添加到converters当中.
        converters.add(fastJsonHttpMessageConverter);
    }

    /**
     * QmBody自定义参数管理器
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JsonPathArgumentResolver());
    }


}
