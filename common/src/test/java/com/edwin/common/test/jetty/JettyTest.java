package com.edwin.common.test.jetty;

import org.junit.Test;

import com.edwin.common.jetty.JettyHttpServer;
import com.edwin.common.jetty.JettyServerConfig;

/**
 * @author jinming
 */
public class JettyTest {

    @Test
    public void testDoStart() throws Exception {
        JettyHttpServer server = new JettyHttpServer();
        JettyServerConfig config = new JettyServerConfig.JettyServerConfigBuilder().maxThreads(4).minThreads(2).httpPort(4080).build();
        server.doStart(config);
        System.in.read();
    }
}
