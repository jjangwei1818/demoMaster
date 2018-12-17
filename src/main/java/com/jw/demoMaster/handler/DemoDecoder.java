package com.jw.demoMaster.handler;

import com.jw.demoMaster.model.DemoRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoDecoder extends LengthFieldBasedFrameDecoder {

    private static Logger logger = LoggerFactory.getLogger(DemoDecoder.class);

    //private static final byte OFFSET = 9;
    private static final byte OFFSET = 11;
    private static final int MAX_LENGTH = 255;
    private static final int LENGTH_OFFSET = 1;
    private static final int FIELD_LENGTH = 1;
    private static final int LENGTH_ADJUSTMENT = 0;
    private static final int INITAIL_BYTES = 0;
    private static final byte BASE_ID_LENGTH = 4;


    /**
     * 255 2 1 0 0 参照报文协议
     */
    public DemoDecoder() {
        super(MAX_LENGTH, LENGTH_OFFSET, FIELD_LENGTH, LENGTH_ADJUSTMENT, INITAIL_BYTES);
        //255  1  1  0  0
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        //处理粘包
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }
        byte[] dd = new byte[frame.readableBytes()];
        frame.getBytes(0, dd);
        logger.info("recv:" + bytes2hex(dd));

        //todo CRC校验
        DemoRequest request = new DemoRequest();
        request.setHead(frame.readByte());
        request.setLength(frame.readByte());
        request.setDeviceId(frame.readIntLE());
        request.setType(frame.readByte());
        //request.setSequence(frame.readUnsignedShortLE());
        request.setSequence(frame.readUnsignedIntLE());

        request.setPayLoad(frame.readBytes(request.getLength() - OFFSET));
        request.setCheckSum(frame.readByte());
        request.setTail(frame.readByte());
        frame.release();
        return request;
    }

    private String bytes2hex(byte[] bytes) {
        final String hex = "0123456789ABCDEF";
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(hex.charAt((b >> 4) & 0x0f));
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(hex.charAt(b & 0x0f));
        }
        return sb.toString();
    }

    public static String decToHex(int dec) {
        String hex = "";
        while (dec != 0) {
            String h = Integer.toString(dec & 0xff, 16);
            if ((h.length() & 0x01) == 1)
                h = '0' + h;
            hex = hex + h;
            dec = dec >> 8;
        }
        return hex;
    }
}
