package com.jw.demoMaster.handler;


import akka.actor.ActorRef;
import com.jw.demoMaster.constant.MessageType;
import com.jw.demoMaster.constant.PayLoadLength;
import com.jw.demoMaster.exception.ErrorPlayLoadException;
import com.jw.demoMaster.executor.ActorFactory;
import com.jw.demoMaster.message.Message;
import com.jw.demoMaster.message.StationRegisterMessage;
import com.jw.demoMaster.message.TagRegisterMessage;
import com.jw.demoMaster.model.DemoRequest;
import com.jw.demoMaster.util.CommonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ChannelHandler.Sharable
public class BizHandler extends ChannelInboundHandlerAdapter {

    private final ActorFactory actorFactory;

    private static Logger logger = LoggerFactory.getLogger(BizHandler.class);

    @Autowired
    public BizHandler(ActorFactory actorFactory) {
        this.actorFactory = actorFactory;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DemoRequest request = null;
        try {
            request = (DemoRequest) msg;
            Message message = transferToBizMessage(request, ctx);

            actorFactory.getDispatcherActor().tell(message, ActorRef.noSender());// message发放

        } catch (Exception e) {
            e.printStackTrace();
            request.getPayLoad().release();
        }
    }

    private Message transferToBizMessage(DemoRequest request, ChannelHandlerContext ctx) {
        int type = request.getType();
        Message message = null;
        switch (type) {
            case MessageType.QUERY_HLR_RSSI:
                if (request.getPayLoad().readableBytes() != PayLoadLength.QUERY_HLR_RSSI) {
                    throw new ErrorPlayLoadException();
                }
                TagRegisterMessage rm = new TagRegisterMessage();
                rm.setBaseId(request.getPayLoad().readIntLE());
                rm.setTagId(request.getPayLoad().readIntLE());
                rm.setRiis(request.getPayLoad().readByte());
                rm.setSequence(request.getSequence());  //返回sequence
                rm.setDeviceId(request.getDeviceId());
                rm.setRecivedTime(new Date());
                rm.setCtx(ctx);
                message = rm;
                logger.info("Type:" + MessageType.QUERY_HLR_RSSI + "|" + rm.getDeviceId() + "|" + rm.getSequence() + "|" + rm.getTagId() + "|" + new Date() + "|reg|");
                request.getPayLoad().release();
                break;
            case MessageType.BASESTATION_REGISTER:
                //todo 长度校验
                StationRegisterMessage stationRegisterMessage = new StationRegisterMessage();
                stationRegisterMessage.setBaseId(request.getPayLoad().readIntLE());
                ByteBuf address = request.getPayLoad().readBytes(4);
                stationRegisterMessage.setAddress(CommonUtils.bytesToAddress(address));
                address.release();
                stationRegisterMessage.setIndex(request.getPayLoad().readByte());
                stationRegisterMessage.setStatus(request.getPayLoad().readByte());
                stationRegisterMessage.setCount(request.getPayLoad().readUnsignedShortLE());
                ByteBuf tags = request.getPayLoad().readBytes(request.getLength() - 23);
                stationRegisterMessage.setTags(CommonUtils.bytesToTags(tags));
                tags.release();
                stationRegisterMessage.setDeviceId(request.getDeviceId());
                stationRegisterMessage.setSequence(request.getSequence());
                stationRegisterMessage.setCtx(ctx);
                message = stationRegisterMessage;
                logger.info("Type:" + MessageType.BASESTATION_REGISTER + "|" + stationRegisterMessage.getDeviceId() + "|" + stationRegisterMessage.getSequence() + "|" + new Date() + "|station register|");
                request.getPayLoad().release();
                break;
            default:
                break;
        }
        return message;
    }


}
