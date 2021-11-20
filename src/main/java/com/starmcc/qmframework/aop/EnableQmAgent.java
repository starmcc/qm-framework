package com.starmcc.qmframework.aop;

import com.starmcc.qmframework.aop.QmControllerAgentAgency;
import com.starmcc.qmframework.config.AgentConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * 使用qm代理
 *
 * @author starmcc
 * @date 2021/11/19
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableConfigurationProperties(AgentConfiguration.class)
@Import(QmControllerAgentAgency.class)
public @interface EnableQmAgent {
}
