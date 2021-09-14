package eightbit.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventTest {

    private Event event;

    @BeforeEach
    void setUp() {
        String description = "NewEvent";
        LocalDateTime dateTime = LocalDateTime.of(2021, 9, 15, 10, 30);
        event = new Event(description, dateTime);
    }

    @Test
    void testToString() {
        String expected = "[E][ ] NewEvent (at: 15 Sep 2021 10:30)";
        assertEquals(expected, event.toString());
    }

    @AfterEach
    void tearDown() {
        event = null;
    }
}
