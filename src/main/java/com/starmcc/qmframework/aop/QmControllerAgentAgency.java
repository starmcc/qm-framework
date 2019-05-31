package com.starmcc.qmframework.aop;

import com.starmcc.qmframework.config.QmFrameConstants;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * 接口日志，返回请求时间，参数，返回值等信息。
 *
 * @Author qm
 * @Date 2018年11月24日 上午1:34:28
 */
@Aspect
public class QmControllerAgentAgency {

    private static final Logger LOG = LoggerFactory.getLogger(QmControllerAgentAgency.class);
    /**
     * 是否打印日志
     */
    private boolean isPrintLogger;
    /**
     * 记录请求时间
     */
    private Long starTime;

    @Autowired(required = false)
    private QmControllerAgent qmControllerAgent;

    private QmControllerAgentAgency() {

    }

    /**
     * 是否打印日志
     *
     * @param isPrintLogger 是否打印
     */
    public QmControllerAgentAgency(boolean isPrintLogger) {
        this.isPrintLogger = isPrintLogger;
    }

    /**
     * 获取QmResponseOut
     *
     * @return
     */
    private final static QmControllerAgent getQmOutMethod() {
        try {
            if (StringUtils.isEmpty(QmFrameConstants.LOGGER_AOP_EXTEND_CLASS) == false) {
                return null;
            }
            return (QmControllerAgent) Class.forName(QmFrameConstants.LOGGER_AOP_EXTEND_CLASS).newInstance();
        } catch (Exception e) {
        }
        return null;
    }



    /**
     * 切入点范围
     */
    @Pointcut("this(com.starmcc.qmframework.controller.QmController)")
    public void qmPointcut() {
    }

    /**
     * 环绕增强(最强版)
     *
     * @param pjp
     * @return
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
        if (qmControllerAgent != null) {
            qmControllerAgent.before(jp);
        }
        if (isPrintLogger) {
            // getTarget得到被代理的目标对象(要切入的目标对象)
            LOG.info("※※※※※※※※※※※※※※※※※※");
            LOG.info("执行位置:" + jp.getTarget().getClass().getName());
            // getSignature得到被代理的目标对象的方法名(返回被切入的目标方法名)
            LOG.info("执行方法:[" + jp.getSignature().getName() + "]");
            // Arrays.toString(jp.getArgs())获得目标方法的参数列表
            LOG.debug("参数列表:" + Arrays.toString(jp.getArgs()));
            LOG.info("※※※※※※※※※※※※※※※※※※");
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
        if (qmControllerAgent != null) {
            qmControllerAgent.afterReturning(jp, result, time);
        }
        if (isPrintLogger) {
            LOG.debug("※※※执行结果:[" + result + "]※※※");
            time = System.currentTimeMillis() - starTime;
            LOG.info("※※※执行耗时：" + time + "/ms※※※");
        }
    }

}
