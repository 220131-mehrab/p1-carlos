import com.revature.p1.domain.Event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testConstructor() {
        Event actual = new Event(1, "Yo");

        Assertions.assertEquals(1, actual.getEventId());
        Assertions.assertEquals("Yo", actual.getEventName());
    }

    @Test
    public void testSetters() {
        Event actual = new Event();

        actual.setEventId(1);
        actual.setEventName("Yo");

        Assertions.assertEquals(1, actual.getEventId());
        Assertions.assertEquals("Yo", actual.getEventName());
    }

    @Test
    public void testToString() {
        Event actual = new Event(1, "Yo");

        String expected = "Event [eventId=1, eventName=Yo]";

        Assertions.assertEquals(expected, actual.toString());
    }
}
