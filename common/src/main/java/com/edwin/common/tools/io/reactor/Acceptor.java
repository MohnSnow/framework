package com.edwin.common.tools.io.reactor;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 处理连接事件
 * 
 * @author jinming
 */
public class Acceptor implements Runnable {

    private final ServerSocketChannel serverSocketChannel;
    private final Selector            selector;
    private static ExecutorService    executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());;

    public Acceptor(ServerSocketChannel serverSocketChannel, Selector selector) {
        this.serverSocketChannel = serverSocketChannel;
        this.selector = selector;
    }

    @Override
    public void run() {

        // 拿到新建立的每个连接的socketchannel，开始处理read/write事件
        SocketChannel socketChannel;
        try {
            socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                executorService.submit(new Handler(socketChannel, selector));
            }
        } catch (IOException e) {
        }
    }

}
