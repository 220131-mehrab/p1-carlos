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

public class RegisteredServlet extends HttpServlet{
    private Connection conn;

    public RegisteredServlet(Connection conn) {
        this.conn = conn;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Team> results = new ArrayList<>();
        String eventId = req.getParameter("eventId");

        if (eventId != null) {
            try {
                String query = "select * from Team where teamId in (select teamId from Registered where eventId = ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, eventId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    results.add(new Team(rs.getInt("teamId"), rs.getString("teamName")));
                }
            } catch (SQLException e) {
                System.err.println("Unable to retrieve data: " + e.getSQLState());
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        String resultString = mapper.writeValueAsString(results);
        resp.setContentType("application/json");
        resp.getWriter().println(resultString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Team regTeam = mapper.readValue(req.getInputStream(), Team.class);

        String eventId = req.getParameter("eventId");

        try {
            String query = "insert into Registered values(?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, eventId);
            stmt.setInt(2, regTeam.getTeamId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to insert: " + e.getSQLState());
        }
    }
}