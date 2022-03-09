package com.revature.p1.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.revature.p1.servlets.DefaultServlet;
import com.revature.p1.servlets.TeamServlet;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * Server class to hold all Tomcat server methods
 */
public class Server {
    Tomcat server;
    String webAppName;

    /**
     * Default constructor initalizes the server
     * @throws SQLException
     */
    public Server() throws SQLException {
        server = new Tomcat();
        webAppName = "";

        server.setBaseDir("java.io.tempdir");
        server.getConnector();
        server.addContext("", null);

        // Defualt servlet
        server.addServlet(webAppName, "defaultServlet", new DefaultServlet()).addMapping("/*");

        // Team Servlet 
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test;INIT=runscript from 'classpath:schema.sql'", "sa", "");
        
        server.addServlet(webAppName, "teamServlet", new TeamServlet(conn)).addMapping("/team");
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