package com.edwin.common.jetty;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;

/**
 * @author jinming
 */
public interface JettyHttpServerProcessor {

    public void preStart(JettyServerConfig serverConfig, Server server, Context context);
}
