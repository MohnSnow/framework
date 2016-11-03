package com.edwin.common.tools.io.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 读写事件处理（Hander执行可以设置为多线程）
 * 
 * @author jinming
 */
public class Handler implements Runnable {

    private final SocketChannel socketChannel;
    private final SelectionKey  selectionKey;

    private static final int    READING    = 0;
    private static final int    SENDING    = 1;

    private ByteBuffer          input      = ByteBuffer.allocate(1024);
    private int                 state      = READING;
    private String              clientName = "";

    Handler(SocketChannel socketChannel, Selector selector) throws IOException {
        this.socketChannel = socketChannel;
        socketChannel.configureBlocking(false);
        selectionKey = this.socketChannel.register(selector, 0);
        selectionKey.attach(this);
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        try {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                send();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void read() throws IOException {
        int readCount = socketChannel.read(input);
        if (readCount > 0) {
            readProcess(readCount);
        }
        state = SENDING;
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    private synchronized void readProcess(int readCount) {
        StringBuilder sb = new StringBuilder();
        input.flip();
        byte[] subStringBytes = new byte[readCount];
        byte[] array = input.array();
        System.arraycopy(array, 0, subStringBytes, 0, readCount);
        sb.append(new String(subStringBytes));
        input.clear();
        clientName = sb.toString().trim();
    }

    private void send() throws IOException {
        ByteBuffer output = ByteBuffer.wrap(("Hello " + clientName + "\n").getBytes());
        socketChannel.write(output);
        selectionKey.interestOps(SelectionKey.OP_READ);
        state = READING;
    }
}
