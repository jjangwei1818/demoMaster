package com.jw.demoMaster.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class TcpServer {

    @Value("${spring.netty.port}")
    private int port;

    private ChannelFuture channelFuture;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    private final ServerChannelInitializer serverChannelInitializer;

    private static Logger logger = LoggerFactory.getLogger(TcpServer.class);


    @Autowired
    public TcpServer(ServerChannelInitializer serverChannelInitializer) {
        this.serverChannelInitializer = serverChannelInitializer;
    }

    @PostConstruct
    public void run() throws Exception {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup(4);
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(serverChannelInitializer)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        // Bind and start to accept incoming connections.
        channelFuture = b.bind(port).sync();

        if (channelFuture.isSuccess()) {
            logger.info(">>>>>----------------------server start {}----------------------<<<<<", port);
        }

    }

    @PreDestroy
    public void stop() {
        try {
            channelFuture.channel().close();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
}

