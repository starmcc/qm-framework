package com.starmcc.qmframework.event;

import org.springframework.context.ApplicationEvent;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author starmcc
 * @version 2020/9/22 9:27
 */
public class QmCommonEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public QmCommonEvent(QmEventModel source) {
        super(source);
    }
}
