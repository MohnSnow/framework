package com.edwin.common.tools.io.reactor;

import java.nio.channels.SelectionKey;

/**
 * 分发事件（可用guava替换）
 * 
 * @author jinming
 */
public class Dispatcher {

    public void dispatch(SelectionKey k) {
        Runnable r = (Runnable) (k.attachment());
        if (r != null) {
            r.run();
        }
    }
}
