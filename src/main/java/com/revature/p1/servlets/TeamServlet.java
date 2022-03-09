package com.revature.p1.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.domain.Team;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet to accept and deliver team data
 */
public class TeamServlet extends HttpServlet{
    private Connection conn;

    public TeamServlet(Connection conn) {
        this.conn = conn;
    }

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Team newTeam = mapper.readValue(req.getInputStream(), Team.class);

        try{
            PreparedStatement stmt = conn.prepareStatement("insert into Team values(?, ?)");
            stmt.setInt(1, newTeam.getTeamId());
            stmt.setString(2, newTeam.getTeamName());
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println("Failed to insert: " + e.getMessage());
        }
    }

}
