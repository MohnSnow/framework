package com.edwin.common;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.server.DatadirCleanupManager;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.apache.zookeeper.server.quorum.QuorumPeerMain;

/**
 * @author jinming
 */
public class MainTest {

    public synchronized void waitMethod() throws InterruptedException {
        this.wait();
        System.out.println("waitMethod");
    }

    public void notifyMethod() throws InterruptedException {
        this.notify();
        System.out.println("notifyMethod");
    }

    public static void main(String args[]) throws Exception {
        
        ZooKeeper zk = new ZooKeeper("localhost:2180,localhost:2181,localhost:2182", 5000, new Watcher() {
            public void process(WatchedEvent event) {
            }
        });
        
        //zookeeper启动
        QuorumPeerMain main = new QuorumPeerMain();
        QuorumPeerConfig config = new QuorumPeerConfig();
        if (args.length == 1) {
            config.parse(args[0]);
        }

        DatadirCleanupManager purgeMgr = new DatadirCleanupManager(config.getDataDir(), config.getDataLogDir(),
                                                                   config.getSnapRetainCount(),
                                                                   config.getPurgeInterval());
        purgeMgr.start();
        main.runFromConfig(config);
        System.exit(0);
    }

}
