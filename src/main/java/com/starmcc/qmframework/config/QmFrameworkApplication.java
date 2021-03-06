package com.starmcc.qmframework.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.starmcc.qmframework.body.JsonPathArgumentResolver;
import com.starmcc.qmframework.event.QmCommonListener;
import com.starmcc.qmframework.event.QmEventManager;
import com.starmcc.qmframework.filter.InitFilter;
import com.starmcc.qmframework.tools.spring.QmSpringManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

/**
 * QmFramework 核心组合配置类
 * 请在标注了@SpringBootApplication启动类中继承他。
 *
 * @Author starmcc
 * @Date 2018年11月24日 上午1:33:11
 */
@Import({TransmitConfiguration.class,
        VersionConfiguration.class,
        SpecialConfiguration.class,
        AesConfiguration.class,
        AgentConfiguration.class,
})
public class QmFrameworkApplication implements WebMvcConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(QmFrameworkApplication.class);

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
        LOG.info("※※※※※※※※※※※※服务已停止※※※※※※※※※※※※");
        LOG.info("浅梦GitHub:{}", GIT_HUB_URL);
    }

    /**
     * 初始化QmSpringManager
     *
     * @return
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
    public FilterRegistrationBean initFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
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
     * 跨域过滤器
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
        config.addAllowedOrigin("*");
        //是否发送Cookie信息
        config.setAllowCredentials(true);
        //放行哪些原始域(请求方式)
        config.addAllowedMethod("*");
        //放行哪些原始域(头部信息)
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Bean
    public QmCommonListener initEventListener(){
        return new QmCommonListener();
    }

    @Bean
    public QmEventManager initListener(){
        return new QmEventManager();
    }


    /**
     * 配置消息转换器--这里我用的是alibaba fastjson
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
