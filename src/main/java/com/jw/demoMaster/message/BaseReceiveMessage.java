package com.jw.demoMaster.message;

import io.netty.channel.ChannelHandlerContext;

import java.io.Serializable;

/**
 * 基础接收消息
 * <p>
 * created on 2017/10/30
 *
 * @author guest
 * @version 1.0
 */
public abstract class BaseReceiveMessage implements Message, Serializable {
    private static final long serialVersionUID = 2514165886424246046L;
    /**
     * ctx
     */
    private ChannelHandlerContext ctx;

    /**
     * 基站标识
     */
    private int deviceId;

    /**
     * 消息标识
     */
    private long sequence;


    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

}
