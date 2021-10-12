package com.starmcc.qmframework.event;

import org.springframework.context.ApplicationListener;

/**
 * @author starmcc
 * @version 2020/9/22 9:29
 */
public class QmCommonListener implements ApplicationListener<QmCommonEvent> {

    @Override
    public void onApplicationEvent(QmCommonEvent event) {
        QmEventModel model = (QmEventModel) event.getSource();
        model.getQmEventInterface().run(model.getData());
    }
}
