package com.revature.p1.domain;

public class Event {
    private int eventId;
    private String eventName;

    public Event() {}

    public Event(int id, String name) {
        setEventId(id);
        setEventName(name);
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "Event [eventId=" + eventId + ", eventName=" + eventName + "]";
    }
}
