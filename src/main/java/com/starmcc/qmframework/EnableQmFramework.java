package com.starmcc.qmframework;

import com.starmcc.qmframework.config.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 使用qm框架应用程序
 *
 * @author starmcc
 * @date 2021/11/19
 */
@Documented
@Import(QmFrameworkConfiguration.class)
@EnableConfigurationProperties({
        TransmitConfiguration.class,
        VersionConfiguration.class,
        SpecialConfiguration.class,
        AesConfiguration.class,
})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableQmFramework {

    String name() default "";

}
