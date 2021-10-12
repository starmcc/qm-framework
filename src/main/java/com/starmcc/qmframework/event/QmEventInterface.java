package com.starmcc.qmframework.event;

/**
 * @author starmcc
 * @version 2020/9/22 12:51
 */
@FunctionalInterface
public interface QmEventInterface {

    /**
     * run
     *
     * @param model
     */
    public void run(Object model);

}
