package com.starmcc.qmframework.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.starmcc.qmframework.aop.QmControllerAgentAgency;
import com.starmcc.qmframework.body.JsonPathArgumentResolver;
import com.starmcc.qmframework.filter.InitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * QmFramework 核心组合配置类
 * 请在标注了@SpringBootApplication启动类中继承他。
 *
 * @Author qm
 * @Date 2018年11月24日 上午1:33:11
 */
public class QmFrameworkApplication implements WebMvcConfigurer {

    /**
     * 项目启动时，只执行一次 Banner 打印
     */
    @PostConstruct
    private final void init() {
        System.out.println("\n" +
                "                  ____    __  __________  ___   __  _______\n" +
                "                 / __ \\  /  |/  / __/ _ \\/ _ | /  |/  / __/\n" +
                "                / /_/ / / /|_/ / _// , _/ __ |/ /|_/ / _/\n" +
                "                \\___\\_\\/_/  /_/_/ /_/|_/_/ |_/_/  /_/___/\n" +
                "         ________________________________________________________\n" +
                "________| 浅梦:gitHub:https://github.com/starmcc                 |_______\n" +
                "\\       | 邮箱:starczt1992@163.com                               |      /\n" +
                " \\      | 开源:[https://github.com/starmcc/QMBoootFrame]         |     /\n" +
                "  |     | 文档:[https://github.com/starmcc/QMBoootFrame/wiki]    |    |\n" +
                " /      |________________________________________________________|    \\\n" +
                "/__________)                                                 (_________\\\n" +
                "                    指若下键万里行，如入浅梦醉逍遥；");
    }

    /**
     * 项目停止执行
     */
    @PreDestroy
    private final void preDestroy() {
        System.out.println("※※※※※※※※※※※※服务已停止※※※※※※※※※※※※");
        System.out.println("浅梦gitHub:https://github.com/starmcc/QMBoootFrame");
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

    /**
     * 初始化过滤器
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
     * 配置消息转换器--这里我用的是alibaba fastjson
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
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JsonPathArgumentResolver());
    }

    /**
     * 注入该DataSource
     *
     * @return 数据源
     */
    @Bean(destroyMethod = "close", initMethod = "init")
    public DataSource settingDataSource() {
        DataSource dataSource = QmDataSourceFactory.getDataSource();
        return dataSource;
    }

}
