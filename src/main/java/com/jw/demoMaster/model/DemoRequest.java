package com.jw.demoMaster.model;

import io.netty.buffer.ByteBuf;

import java.io.Serializable;

public class DemoRequest implements Serializable {
    private static final long serialVersionUID = 2644660001508901615L;

    private int head;

    private int length;

    private int deviceId;

    private int type;

    private long sequence;

    private ByteBuf payLoad;

    private int checkSum;

    private int tail;

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public ByteBuf getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(ByteBuf payLoad) {
        this.payLoad = payLoad;
    }

    public int getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(int checkSum) {
        this.checkSum = checkSum;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }
}
