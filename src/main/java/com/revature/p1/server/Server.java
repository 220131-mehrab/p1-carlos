package com.revature.p1.server;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.domain.Team;

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
     * @throws SQLException
     */
    public Server() throws SQLException {
        server = new Tomcat();
        webAppName = "";

        server.setBaseDir("java.io.tempdir");
        server.getConnector();
        server.addContext("", null);

        // Defualt servlet
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
        }).addMapping("/*");

        // Team Servlet 
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test;INIT=runscript from 'classpath:schema.sql'", "sa", "");
        
        server.addServlet(webAppName, "teamServlet", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                List<Team> teams = new ArrayList<>();
                
                try {
                    ResultSet rs = conn.prepareStatement("select * from Team").executeQuery();

                    while (rs.next()) {
                        teams.add(new Team(rs.getInt("teamId"), rs.getString("teamName")));
                    }
                } catch (SQLException e) {
                    System.err.println("Failed to retrieve from db: " + e.getSQLState());
                }
                
                // Object Mapper
                ObjectMapper mapper = new ObjectMapper();
                String results = mapper.writeValueAsString(teams);
                resp.setContentType("application/json");
                resp.getWriter().println(results);
            }
        }).addMapping("/team");
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