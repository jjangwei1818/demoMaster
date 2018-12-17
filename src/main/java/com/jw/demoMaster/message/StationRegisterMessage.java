package com.jw.demoMaster.message;

/**
 * 基站注册消息
 * created on 2017/11/2
 *
 * @author guest
 */
public class StationRegisterMessage extends BaseReceiveMessage {

    private static final long serialVersionUID = -9159184934233388566L;

    private int baseId;

    private String address;

    private int index;

    /**
     * 0初始化（数据丢失） 1 重连(数据没丢失)
     */
    private int status;
    /**
     * 掉线标签总数
     */
    private int count;
    /**
     * 掉线标签id 4bytes
     */
    private int[] tags;

    public int getBaseId() {
        return baseId;
    }

    public void setBaseId(int baseId) {
        this.baseId = baseId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int[] getTags() {
        return tags;
    }

    public void setTags(int[] tags) {
        this.tags = tags;
    }
}
