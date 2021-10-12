package com.starmcc.qmframework.aop;

import com.starmcc.qmframework.config.AgentConfiguration;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * @author starmcc
 * @version 2018年11月24日 上午1:34:28
 * 接口日志，返回请求时间，参数，返回值等信息。
 */
@Aspect
public class QmControllerAgentAgency {

    private static final Logger LOG = LoggerFactory.getLogger(QmControllerAgentAgency.class);

    /**
     * 记录请求时间
     */
    private Long starTime;

    @Autowired(required = false)
    private QmControllerAgent qmControllerAgent;

    private QmControllerAgentAgency() {

    }


    /**
     * 切入点范围
     */
    @Pointcut("@annotation(com.starmcc.qmframework.aop.QmAgent)")
    public void qmPointcut() {
    }

    /**
     * 环绕增强(最强版)
     *
     * @param pjp
     * @return Returns the specified data according to the method
     * @throws Throwable
     */
    @Around("qmPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }

    /**
     * 前置增强
     *
     * @param jp
     */
    @Before("qmPointcut()")
    public void before(JoinPoint jp) {
        starTime = System.currentTimeMillis();
        if (null != qmControllerAgent) {
            qmControllerAgent.before(jp);
        }
        if (AgentConfiguration.log) {
            // getTarget得到被代理的目标对象(要切入的目标对象)
            // getSignature得到被代理的目标对象的方法名(返回被切入的目标方法名)
            LOG.debug("执行位置:{}.{}", jp.getTarget().getClass().getName(),
                    jp.getSignature().getName());
            // Arrays.toString(jp.getArgs())获得目标方法的参数列表
            LOG.debug("参数列表:" + Arrays.toString(jp.getArgs()));
        }
    }

    /**
     * 后置增强
     *
     * @param jp
     * @param result 方法返回的结果
     */
    @AfterReturning(pointcut = "qmPointcut()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        Long time = System.currentTimeMillis() - starTime;
        if (null != qmControllerAgent) {
            qmControllerAgent.afterReturning(jp, result, time);
        }
        if (AgentConfiguration.log) {
            LOG.debug("※※※执行结果:{}", result);
            time = System.currentTimeMillis() - starTime;
            LOG.info("※※※执行耗时：{}/ms", time);
        }
    }

}
