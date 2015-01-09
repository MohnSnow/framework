package com.edwin.interceptor.intercept;

/**
 * @author jinming.wu
 * @date 2015-1-9
 */
public class PluginException extends Exception {

    private static final long serialVersionUID = 6657273558193462809L;

    protected String          name;

    public PluginException() {
        super();
    }

    public PluginException(String message) {
        super(message);
    }

    public PluginException(Throwable cause) {
        super(cause);
    }

    public PluginException(String message, Throwable cause) {
        super(message, cause);
    }

    public Throwable getRootCause() {
        Throwable rootCause = null;
        Throwable cause = getCause();
        while (cause != null && cause != rootCause) {
            rootCause = cause;
            cause = cause.getCause();
        }
        return rootCause;
    }

    public String getName() {
        return this.name;
    }
}
