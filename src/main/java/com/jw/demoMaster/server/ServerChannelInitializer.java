package com.jw.demoMaster.server;

import com.jw.demoMaster.handler.BizHandler;
import com.jw.demoMaster.handler.DemoDecoder;
import com.jw.demoMaster.handler.ExceptionHandler;
import com.jw.demoMaster.handler.LoginAuthHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final LoginAuthHandler loginAuthHandler;

    private final BizHandler bizHandler;

    private final ExceptionHandler exceptionHandler;

    @Autowired
    public ServerChannelInitializer(LoginAuthHandler loginAuthHandler,
                                    BizHandler bizHandler, ExceptionHandler exceptionHandler) {
        this.loginAuthHandler = loginAuthHandler;
        this.bizHandler = bizHandler;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LoggingHandler())
//                .addLast(new LinkDetectionHandler(60, TimeUnit.SECONDS))    //链路检测用
                .addLast(new DemoDecoder())
//                .addLast(new DemoEncoder())
                .addLast(loginAuthHandler)
                .addLast(bizHandler)
                .addLast(new ExceptionHandler());

    }
}
