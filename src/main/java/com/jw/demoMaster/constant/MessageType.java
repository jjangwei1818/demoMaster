package com.jw.demoMaster.constant;

/**
 * 定义了指令的类型
 *
 * @author guest
 */
public class MessageType {

    /**
     * 0x74  标签注册
     */
    public static final int QUERY_HLR_RSSI = 116;
    /**
     * 0x75
     */
    public static final int QUERY_HLR_RSSI_ACK = 117;
    /**
     * 0x68
     */
    public static final int RSSI_OFFLINE_IND = 104;
    /**
     * 0x69
     */
    public static final int RSSI_OFFLINE_IND_ACK = 105;
    public static final int WRITE_IMAGE_DATA_INFO = 0x70;
    public static final int WRITE_IMAGE_DATA_INFO_ACK = 0x73;
    public static final int WRITE_IMAGE_DATA = 0x72;
    public static final int WRITE_IMAGE_DATA_ACK = 0x79;
    /**
     * 0x80
     */
    public static final int BASESTATION_REGISTER = -128;
    /**
     * 0x81
     */
    public static final int BASESTATION_REGISTER_ACK = -127;
    /**
     * 0x82
     */
    public static final int UPLOAD_HANDSET_QUERY = -126;
    /**
     * 0x83
     */
    public static final int UPLOAD_HANDSET_QUERY_ACK = -125;
    /**
     * 0x7a
     */
    public static final int TAG_STATE_UPLOAD = 122;
    /**
     * 0x84
     */
    public static final int BASE_HEART_BEAT = -124;

    public static final int SERVER_HEART_BEAT = -123;
    /**
     * 0x71
     */
    public static final int BIND_TAG_BARCODE = 113;

    /**
     * 0x62
     */
    public static final int WRITE_PRICEBUF = 98;
    /**
     * 0x63
     */
    public static final int WRITE_PRICEBUF_ACK = 99;
    /**
     * 0x87
     */
    public static final int UN_BANDING = -121;

    public static void main(String[] args) {
        byte i = (byte) 0x87;
        System.out.println(i);
    }
}

