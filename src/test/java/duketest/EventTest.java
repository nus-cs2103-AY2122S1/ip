package duketest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Event;

public class EventTest {
    @Test
    public void toTxtTest() {
        Event event = new Event("Attend meeting", "2021-08-24 1600");
        assertEquals("E | 0 | Attend meeting | Aug 24 2021 4.00 PM", event.toTxt());
    }
}
