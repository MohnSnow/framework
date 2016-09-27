package com.edwin.common.jetty;

import java.util.List;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.thread.QueuedThreadPool;

import com.edwin.common.tools.ExtensionLoader;
import com.edwin.common.tools.io.NetUtils;

/**
 * @author jinming
 */
public class JettyHttpServer {

    private volatile boolean started = false;

    private Server           server;
    private int              port;
    private Object           lock    = new Object();

    public void doStart(JettyServerConfig config) throws Exception {
        if (!started) {
            synchronized (lock) {
                if (!started) {
                    server = newServer(config);
                    server.start();
                    started = true;
                }
            }
        }
    }

    public void doStop() throws Exception {
        server.stop();
    }

    public static void main(String args[]) throws Exception {
        JettyHttpServer server = new JettyHttpServer();
        JettyServerConfig config = new JettyServerConfig.JettyServerConfigBuilder().maxThreads(4).minThreads(2).httpPort(4080).build();
        server.doStart(config);
        System.in.read();
    }

    protected Server newServer(JettyServerConfig config) {
        this.port = NetUtils.getAvailablePort(config.getHttpPort());
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setDaemon(true);
        threadPool.setMaxThreads(config.getMaxThreads());
        threadPool.setMinThreads(config.getMinThreads());
        Server server = new Server(port);
        server.setThreadPool(threadPool);
        Context context = new Context(Context.SESSIONS);
        context.setContextPath("/");
        server.addHandler(context);
        List<JettyHttpServerProcessor> processors = ExtensionLoader.getExtensionList(JettyHttpServerProcessor.class);
        if (processors != null) {
            for (JettyHttpServerProcessor processor : processors) {
                processor.preStart(config, server, context);
            }
        }
        return server;
    }
}
