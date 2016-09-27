package com.edwin.common.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jinming
 */
public class ServiceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ServiceServlet(JettyServerConfig serverConfig, int port) {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                   IOException {
        response.setContentType(getContentType());
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Hello, Jetty!");
    }

    public String getContentType() {
        return "text/html; charset=UTF-8";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
