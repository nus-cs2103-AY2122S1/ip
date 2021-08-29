package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    /**
     * Tests Event constructor.
     */
    @Test
    public void testEventConstructor() {
        Event event = new Event("Hackathon kick-off", "2021-09-22", "19:00");
        String expected = "[E][ ] Hackathon kick-off (at: Sep 22 2021 7:00 PM)";
        assertEquals(expected, event.toString());
    }

}
