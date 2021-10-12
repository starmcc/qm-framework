package com.starmcc.qmframework.event;

import java.util.Objects;

/**
 * @author starmcc
 * @version 2020/9/22 9:54
 */
public class QmEventModel {

    private Object data;
    private Object[] datas;
    private QmEventInterface qmEventInterface;

    private QmEventModel() {
    }

    protected QmEventModel(Object data,QmEventInterface qmEventInterface) {
        this.data = data;
        this.qmEventInterface = qmEventInterface;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public QmEventInterface getQmEventInterface() {
        return qmEventInterface;
    }

    public void setQmEventInterface(QmEventInterface qmEventInterface) {
        this.qmEventInterface = qmEventInterface;
    }
}
