package com.edwin.common.tools.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author jinming
 */
public class NettyServer {

    public static void main(String[] args) {
        
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,
                                  workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG,
                                                                                            200).childHandler(new ChannelInitializer<SocketChannel>() {

                                                                                                @Override
                                                                                                protected void initChannel(SocketChannel ch) throws Exception {
                                                                                                    // ch.pipeline().addLast(new
                                                                                                    // LengthFieldBasedFrameDecoder(80,
                                                                                                    // 0,
                                                                                                    // 4,
                                                                                                    // 0,
                                                                                                    // 4));
                                                                                                    // ch.pipeline().addLast(new
                                                                                                    // StringDecoder(Charset.forName("UTF-8")));
                                                                                                }
                                                                                            });
            ChannelFuture f = serverBootstrap.bind(8080).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
