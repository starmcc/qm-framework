package com.starmcc.qmframework.event;

/**
 * @Author starmcc
 * @Date 2020/9/22 12:51
 */
@FunctionalInterface
public interface QmEventInterface {

    public void run(Object model);

}
