package com.jw.demoMaster.handler;


import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class LoginAuthHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(LoginAuthHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //todo
        logger.info("开始连接验证" + ctx.channel().toString());
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //继续传递，不然后面的handler的这方法会无法执行
        super.channelRead(ctx, msg);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info(ctx.toString() + "断开连接");
        super.channelInactive(ctx);
    }
}

