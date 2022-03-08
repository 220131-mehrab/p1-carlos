package com.revature.p1.domain;

/**
 * FIRST Robotics Competition Team Object
 */
public class Team {
    private int teamId;
    private String teamName;

    public Team(int id, String name) {
        teamId = id;
        teamName = name;
    }

    public Team(){}

    public int getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamId(int id) {
        teamId = id;
    }

    public void setTeamName(String name) {
        teamName = name;
    }

    @Override
    public String toString() {
        return "Team [teamId=" + teamId + ", name=" + teamName + "]";
    }
}
