import com.revature.p1.domain.Team;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TeamTest {

    @Test
    public void testConstructor() {
        Team actual = new Team(1, "Yo");

        Assertions.assertEquals(1, actual.getTeamId());
        Assertions.assertEquals("Yo", actual.getTeamName());
    }

    @Test
    public void testSetters() {
        Team actual = new Team();

        actual.setTeamId(1);
        actual.setTeamName("Yo");

        Assertions.assertEquals(1, actual.getTeamId());
        Assertions.assertEquals("Yo", actual.getTeamName());
    }

    @Test
    public void testToString() {
        Team actual = new Team(1, "Yo");

        String expected = "Team [teamId=1, name=Yo]";

        Assertions.assertEquals(expected, actual.toString());
    }
}
