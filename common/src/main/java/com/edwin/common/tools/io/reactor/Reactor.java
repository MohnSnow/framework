package com.edwin.common.tools.io.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 管理所有socket和事件（链接、数据读/写），基于事件的处理可以用Guava将Acceptor、Dispatcher、Handler替换
 * 
 * @author jinming
 */
public class Reactor implements Runnable {

    private final Selector            selector;
    private final ServerSocketChannel serverSocketChannel;
    private final Dispatcher          dispatcher;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        SelectionKey selectionKey0 = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey0.attach(new Acceptor(serverSocketChannel, selector));
        dispatcher = new Dispatcher();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                int readySelectionKeyCount = selector.select();
                if (readySelectionKeyCount == 0) {
                    continue;
                }
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()) {
                    dispatcher.dispatch((SelectionKey) (it.next()));
                }

                // 不会自动remove，因此要手动清；下次事件到来会自动添加
                selected.clear();
            }
        } catch (IOException ex) {

        }
    }
}
