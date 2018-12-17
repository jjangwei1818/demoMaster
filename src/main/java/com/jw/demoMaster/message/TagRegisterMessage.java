package com.jw.demoMaster.message;

import java.util.Date;

/**
 * 标签注册消息
 *
 * @author guest
 */
public class TagRegisterMessage extends BaseReceiveMessage {

    private static final long serialVersionUID = -4716427705636432704L;

    private int baseId;

    private int tagId;

    private int riis;

    private Date recivedTime;

    public int getBaseId() {
        return baseId;
    }

    public void setBaseId(int baseId) {
        this.baseId = baseId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getRiis() {
        return riis;
    }

    public void setRiis(int riis) {
        this.riis = riis;
    }

    public Date getRecivedTime() {
        return recivedTime;
    }

    public void setRecivedTime(Date recivedTime) {
        this.recivedTime = recivedTime;
    }
}

