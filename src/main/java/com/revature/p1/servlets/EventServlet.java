package com.revature.p1.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.domain.Event;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EventServlet extends HttpServlet {
    Connection conn;

    public EventServlet(Connection conn) {
        this.conn = conn;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Event> events = new ArrayList<>();
        String searchId = req.getParameter("eventId");

        if (searchId == null) {
            try {
                ResultSet rs = conn.prepareStatement("select * from Event").executeQuery();

                while (rs.next())
                    events.add(new Event(rs.getInt("eventId"), rs.getString("eventName")));
            } catch (SQLException e) {
                System.err.println("Failed to retrieve from db: " + e.getSQLState());
            }
        } else {
            try {
                PreparedStatement stmt = conn.prepareStatement("select * from Event where eventId = ?");
                stmt.setString(1, searchId);

                ResultSet rs = stmt.executeQuery();

                while (rs.next())
                    events.add(new Event(rs.getInt("eventId"), rs.getString("eventName")));
            } catch (SQLException e) {
                System.err.println("Failed to retrieve from db: " + e.getSQLState());
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        String results = mapper.writeValueAsString(events);
        resp.setContentType("application/json");
        resp.getWriter().println(results);
    }
}
