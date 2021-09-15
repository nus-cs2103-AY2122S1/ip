package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    DateTimeHandler dth = new DateTimeHandler();
    Event e = new Event("test1", false, dth.parseDate("22/08/2021 1500"), new ArrayList<>());
    @Test
    public void testEvent() {
        assertEquals(e.typeIcon(), "[E]");
        assertEquals(e.toString(), "[E] [-] test1 (at: 2021-08-22T15:00)");

        e.completeTask();
        assertEquals(e.toString(), "[E] [X] test1 (at: 2021-08-22T15:00)");
    }
}
