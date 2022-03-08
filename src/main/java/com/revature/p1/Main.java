package com.revature.p1;

import java.sql.SQLException;

import com.revature.p1.server.Server;

public class Main {
    public static void main(String[] args) throws SQLException {
        Server server = new Server();
        server.run();
    }
}
