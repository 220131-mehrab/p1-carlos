package com.revature.p1.server;

import java.io.IOException;
import java.io.InputStream;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Server class to hold all Tomcat server methods
 */
public class Server {
    Tomcat server;
    String webAppName;

    /**
     * Default constructor initalizes the server
     */
    public Server() {
        server = new Tomcat();
        webAppName = "";

        server.setBaseDir("java.io.tempdir");
        server.getConnector();
        server.addContext("", null);

        server.addServlet(webAppName, "defaultServlet", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                
                        String filename = req.getPathInfo();
                        String resourceDir = "static";
                        InputStream file = getClass().getClassLoader().getResourceAsStream(resourceDir + filename);
                        String mimeType = getServletContext().getMimeType(filename);
                        resp.setContentType(mimeType);

                        IOUtils.copy(file, resp.getOutputStream());
            }
        }).addMapping("/*");;
    }

    /**
     * Starts server
     */
    public void run() {
        try {
            server.start();
        } catch (LifecycleException e) {
            System.err.println("Failed to start server: " + e.getMessage());
        }
    }
}