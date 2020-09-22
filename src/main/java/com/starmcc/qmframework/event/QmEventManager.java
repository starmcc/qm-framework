package com.starmcc.qmframework.event;

import com.starmcc.qmframework.tools.spring.QmSpringManager;
import org.springframework.scheduling.annotation.Async;

/**
 * @Author starmcc
 * @Date 2020/9/22 9:57
 */
public class QmEventManager {


    /**
     * 触发自定义事件
     *
     * @param clamm
     * @param datas
     */
    public void publishEvent(Class<? extends QmEventInterface> clamm, Object... datas) {
        QmEventInterface bean = QmSpringManager.getBean(clamm);
        QmEventModel model = new QmEventModel(datas, bean);
        QmSpringManager.getApplicationContext().publishEvent(new QmCommonEvent(model));
    }

    /**
     * 触发自定义事件(异步调用 启动类需增加注解：@EnableAsync)
     *
     * @param clamm
     * @param datas
     */
    @Async
    public void publishAsyncEvent(Class<? extends QmEventInterface> clamm, Object... datas) {
        this.publishEvent(clamm, datas);
    }

}
