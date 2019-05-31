package com.starmcc.qmframework.aop;

import org.aspectj.lang.JoinPoint;

/**
 * 请求方法切入代理控制器
 *
 * @Author qm
 * @Date 2019/1/7 0:57
 */
public interface QmControllerAgent {

    /**
     * 执行请求方法前执行该方法(前置增强)
     *
     * @param jp JoinPoint 切入点对象
     */
    void before(JoinPoint jp);


    /**
     * 当请求方法执行完毕时执行该方法(后置增强)
     *
     * @param jp           JoinPoint 切入点对象
     * @param result       返回结果
     * @param responseTime 响应时间
     */
    void afterReturning(JoinPoint jp, Object result, Long responseTime);

}
