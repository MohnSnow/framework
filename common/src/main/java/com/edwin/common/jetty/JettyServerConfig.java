package com.edwin.common.jetty;

/**
 * @author jinming {simple}
 */
public class JettyServerConfig {

    public static final int DEFAULT_HTTP_PORT = 4080;

    private final int       maxThreads;

    private final int       minThreads;

    private final int       httpPort;

    private JettyServerConfig(JettyServerConfigBuilder builder) {
        this.maxThreads = builder.maxThreads;
        this.minThreads = builder.minThreads;
        this.httpPort = builder.httpPort;
    }

    public static class JettyServerConfigBuilder {

        private int maxThreads;
        private int minThreads;
        private int httpPort;

        public JettyServerConfigBuilder() {

        }

        public JettyServerConfigBuilder maxThreads(int maxThreads) {
            this.maxThreads = maxThreads;
            return this;
        }

        public JettyServerConfigBuilder minThreads(int minThreads) {
            this.minThreads = minThreads;
            return this;
        }

        public JettyServerConfigBuilder httpPort(int httpPort) {
            this.httpPort = httpPort;
            return this;
        }

        public JettyServerConfig build() {
            return new JettyServerConfig(this);
        }
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public int getMinThreads() {
        return minThreads;
    }

    public int getHttpPort() {
        return httpPort;
    }
}
