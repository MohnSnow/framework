package com.edwin.common.jetty;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

/**
 * @author jinming
 */
public class DefautJettyProcessor implements JettyHttpServerProcessor {

    @Override
    public void preStart(JettyServerConfig serverConfig, Server server, Context context) {
        int port = server.getConnectors()[0].getPort();
        context.addServlet(new ServletHolder(new ServiceServlet(serverConfig, port)), "/services");
    }
}
