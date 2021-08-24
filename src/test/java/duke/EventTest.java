package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void test() {
        Event event = new Event("Dance", "2020-01-09");
        assertEquals("E | | Dance | Jan 9 2020", event.toString());
    }
}
