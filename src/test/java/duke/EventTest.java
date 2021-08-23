package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion(){
        assertEquals("[E][ ] Skate Clinic (at: 24/8 6.30pm)",
                new Event("Skate Clinic", "24/8 6.30pm").toString());
    }

    @Test
    public void testGetAt(){
        assertEquals("Tuesday 2359",
                new Event("CS2103T iP homework", "Tuesday 2359").getAt());
    }
}